package RegisterMian;
//Name:Ibrahim Ibrahim
//Neptun code: JN8VJ8

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class XmlWriter {

    private static Scanner s = new Scanner(System.in); // Creating two different instances of scanner
    private static Scanner s1 = new Scanner(System.in); // 's' for integers and 's1' for strings

    public static void main(String[] args) {
        String filepath = "C:\\ProgrammingProjects\\Java_Projects\\StipendiumHungaricumApplication\\src\\Resources\\students_data.xml";
        // Creating the collection of the student
        ArrayList<Student> students = XmlReader.readStudentsFromXml(filepath);// Creating the collection of the student

        char choice_upper;
        try{
            do{ // using a do_while loop tp iterate over and over again until the user input 'Q'
                menu();
                /*choice is a char type. AND scanner.next() returns a String,
                SO I can't assign a String to a char.But what I can do is assigning
                the first character of the String to a char,Using charAt(0); */

                char choice = s1.next(".").charAt(0);
                choice_upper = Character.toUpperCase(choice);// convert what the user input into an uppercase

                switch (choice_upper) {
                    case 'R' -> RegisterStudent(students);
                    case 'L' -> listStudent(students);
                    case 'C' -> clearStudent(students);
                    case 'S' -> searchStudentByID(students);
                    case 'F' -> searchStudentByName(students);
                    case 'D' -> deleteStudentByID(students);
                    case 'T' -> deleteStudentByName(students);
                    case 'U' -> updateStudentByID(students);
                    case 'G' -> updateStudentByName(students);
                    case 'Q' -> System.out.println("Goodbye...");
                    default -> System.out.println("Illegal choice\tTry Again!");
                }

                saveStudentsToXml(students, filepath);

            }while(choice_upper!='Q');
        }catch (InputMismatchException e) {
            System.out.println("Must Be One Character From The menu");
        }
    }


    private static void menu() {
        System.out.println("\nWelcome To Stipendium Hungaricum Scholarship Application System");
        System.out.println("Please Select A Menu Point: ");
        System.out.println("\n\tR - Register A Student");
        System.out.println("\tL - List All Students");
        System.out.println("\tC - Clear All Students");
        System.out.println("\tS - Search A Student By Its Application ID");
        System.out.println("\tF - Search A Student By Tts Name");
        System.out.println("\tD - Delete A Student By Tts Application ID");
        System.out.println("\tT - Delete A Student By Tts Name");
        System.out.println("\tU - Update A Student By Tts Application ID");
        System.out.println("\tG - Update A Student By Tts Name");

        System.out.println("\tQ - Quit  ");
        System.out.print("\nEnter Your Choice: ");
    }

    private static void RegisterStudent(ArrayList<Student> students) {
        int students_to_add = inputStudentsToAdd();
        s1.nextLine(); //throw away the \n not consumed by nextInt()
        // for loop to register the asked number of students one by one
        for (int j = 0; j < students_to_add; j++){
            System.out.print("Enter Student Name: ");
            String name = s1.nextLine();
            System.out.print("Enter Student Email Address: ");
            String emailAddress = s1.nextLine();
            Gender gender = inputGender();
            int app_id = inputApp_id();
            boolean found = false;// Assuming there is no duplicated students
            found =checkDuplicate(students,app_id );//checking for duplicated students
            if (found) {//found will be set to true if a duplicated student found
                System.out.println("Student with this application ID is already registered");
            }
            else {
                int age = inputAge();
                if (age >= 18 && age <= 40) {
                    students.add(new Student(app_id, name, age, emailAddress, gender)); // add the data to ArrayList
                } else {
                    System.out.println("Your Are Not Eligible To Apply For The Stipendium Hungaricum Scholarship");
                    System.out.println("Students Between 18-40 Are Eligible To Apply For The Hungaricum Scholarship");
                }
            }
            System.out.println();
        }
    }


    private static void listStudent(ArrayList<Student> students) {
        if (students.size()==0){
            System.out.println("There Is No Students!!\tRegister A Student First");
        }
        else {
            System.out.println("----------------------------");
            System.out.println("Name       \tID    \tAge  \tEmail Address  \t\tGender");
            //Using For-Each Loop to loop through each and every student data the print it
            for (Student student : students) {
                System.out.println(student);
            }
            System.out.println("----------------------------");
        }
    }


    private static void updateStudentByName(ArrayList<Student> students) {
        if (students.size()==0){
            System.out.println("There Is No Students!!\tRegister A Student First");
        }
        else {
            Student student = findStudentByName(students);
            update(students, student);
        }

    }
    private static void updateStudentByID(ArrayList<Student> students) {
        if (students.size()==0){
            System.out.println("There Is No Students!!\tRegister A Student First");
        }
        else {
            Student student = findStudentIdByID(students);
            update(students, student);
        }
    }
    private static void update(ArrayList<Student> students, Student student) {
        s1.nextLine(); //throw away the \n not consumed by nextInt()
        System.out.print("Enter New Name: ");
        String name = s1.nextLine();
        System.out.print("Enter Student new Email Address: ");
        String emailAddress = s1.nextLine();
        Gender gender = inputGender();
        int age=inputAge();
        if (age >= 18 && age <= 40) {
            students.set(students.indexOf(student),new Student(student.getApp_id(),name,age, emailAddress, gender));
        } else {
            System.out.println("Your Are Not Eligible To Apply For The Stipendium Hungaricum Scholarship");
            System.out.println("Students Between 18-40 Are Eligible To Apply For The Hungaricum Scholarship");
        }
    }

    
    private static void searchStudentByName(ArrayList<Student> students) {
        if (students.size()==0){
            System.out.println("There Is No Students!!\tRegister A Student First");
        }
        else {
            Student student = findStudentByName(students);
            displaySearch(student);
        }
    }
    private static void searchStudentByID(ArrayList<Student> students) {
        if (students.size()==0){
            System.out.println("There Is No Students!!\tRegister A Student First");
        }
        else {
            Student student = findStudentIdByID(students);
            displaySearch(student);
        }
    }
    private static void displaySearch(Student student) {
        System.out.println("----------------------------");
        System.out.println("Name      \tID    \tAge  \tEmail Address  \tGender");
        System.out.println(student);
        System.out.println("----------------------------");
    }

    private static void deleteStudentByName(ArrayList<Student> students) {
        if (students.size()==0){
            System.out.println("There Is No Students!!\tRegister A Student First");
        }
        else {
            //remove function will delete that particular student if it's found
            students.remove(findStudentByName(students));
        }
    }
    private static void deleteStudentByID(ArrayList<Student> students) {
        if (students.size()==0){
            System.out.println("There Is No Students!!\tRegister A Student First");
        }
        else {
            //remove function will delete that particular student if it's found
            students.remove(findStudentIdByID(students));
        }
    }
    
    
    private static Student findStudentByName(ArrayList<Student> students) {
        Student student = new Student();
        String name="";
        s1.nextLine(); //throw away the \n not consumed by nextInt()
        while (name.isEmpty()) {
            System.out.print("Enter Student's Name: ");
            name = s1.nextLine();
            System.out.println();
            for (Student studentElement : students) {
                if (studentElement.getName().equals(name)) {
                    return studentElement;
                }
            }
            name = "";
            System.out.println("Student with this name Not Found");
        }
        return student;
    }
    private static Student findStudentIdByID(ArrayList<Student> students) {
        Student student = new Student();
        int app_id = 0;
        while (app_id == 0) {
            app_id = inputApp_id();
            System.out.println();
            for (Student studentElement : students) {
                if (studentElement.getApp_id() == app_id) {
                    return studentElement;
                }
            }
            app_id = 0;
            System.out.println("Student With This Application ID Not Found!");
        }
        return student;
    }


    private static void clearStudent(ArrayList<Student> students) {
        if (students.size()==0){
            System.out.println("There Is No Students!!\tRegister A Student First");
        }
        else {
            confirmClear(students);
        }
    }
    private static void confirmClear(ArrayList<Student> students) {
        System.out.print("Are You Sure You Want To Clear The Students List? (Y/N): ");
        char confirm = s.next(".").charAt(0);
        if (confirm == 'y' || confirm == 'Y') {
            students.clear();
            System.out.println("The List Of Students Has Been Cleared Successfully...!");
        } else if (confirm == 'n' || confirm == 'N') {
            System.out.println();
        } else
            System.out.println("Illegal Choice");
    }


    public static void saveStudentsToXml(ArrayList<Student> students, String filepath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element rootElement = document.createElement("students");
            document.appendChild(rootElement);

            for (Student student : students) {
                Element studentElement = document.createElement("student");
                rootElement.appendChild(studentElement);
                createChildElement(document, studentElement, "app_id", String.valueOf(student.getApp_id()));
                createChildElement(document, studentElement, "name", student.getName());
                createChildElement(document, studentElement, "age", String.valueOf(student.getAge()));
                createChildElement(document, studentElement, "emailAddress", student.getEmailAddress());
                createChildElement(document, studentElement, "gender", student.getGender().toString());
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void createChildElement(Document document, Element parent, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }

    private static Gender inputGender() {
        Gender gender = Gender.MALE;
        String rawInput = "";
        while (rawInput.isEmpty()) {
            try {
                System.out.print("Enter Student Gender: ");
                rawInput = s1.nextLine();
                gender = Gender.valueOf(rawInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Not valid.");
                rawInput = "";
            }
        }
        return gender;
    }
    private static int inputStudentsToAdd() {
        int studentsToAdd = 0;
        while (studentsToAdd == 0) {
            try {
                System.out.print("Enter How Many Students You Want To Register: ");
                studentsToAdd = s.nextInt();
                s.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Must Be An Integer Not Containing Letters");
                s.nextLine();
            }
        }
        return studentsToAdd;
    }
    private static int inputAge() {
        int age = 0;
        while (age == 0) {
            try {
                System.out.print("Enter New Age : ");
                age = s.nextInt();
                s.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("The Age Must Be An Integer Not Containing Letters ");
                s.nextLine();
            }
        }
        return age;
    }
    private static int inputApp_id() {
        int app_id = 0;
        while (app_id==0) {
            try {
                System.out.print("Enter Student Application ID: ");
                app_id = s.nextInt();
                s.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("The Application ID Must Be An Integer Not Containing Letters");
                s.nextLine();
            }
        }
        return app_id;
    }

    private static boolean checkDuplicate(ArrayList<Student> students,int app_id ){
        boolean found= false;// search the entire list using iterator for the student with that app_id
        for (Student studentElement : students) {
            if (studentElement.getApp_id()== app_id) {
                found = true;//set to true if we find duplicated student ID
                break;
            }
        }
        return found;
    }
}
