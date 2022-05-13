package RegisterMian;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.util.ArrayList;

public class XmlReader {

    public static ArrayList<Student> readStudentsFromXml(String filepath) {
        ArrayList<Student> Students = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new FileInputStream(filepath));

            Element rootElement = document.getDocumentElement();

            NodeList childOfRootElement = rootElement.getChildNodes();

            for (int i = 0; i < childOfRootElement.getLength(); i++) {
                Node childNode = childOfRootElement.item(i);  // get a child node object
                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList childOfStudentTag = childNode.getChildNodes();  // get all child nodes of a user tag
                    int app_id = 0;
                    String name = "";
                    int age = 0;
                    String emailAddress= "";
                    Gender gender = Gender.MALE;
                    for (int j = 0; j < childOfStudentTag.getLength(); j++) {
                        Node childNodeOfStudentTag = childOfStudentTag.item(j);
                        if (childNodeOfStudentTag.getNodeType() == Node.ELEMENT_NODE) {
                            switch (childNodeOfStudentTag.getNodeName()) {
                                case "app_id" -> app_id = Integer.parseInt(childNodeOfStudentTag.getTextContent());
                                case "name" -> name = childNodeOfStudentTag.getTextContent();
                                case "age" -> age = Integer.parseInt(childNodeOfStudentTag.getTextContent());
                                case "emailAddress" -> emailAddress = childNodeOfStudentTag.getTextContent();
                                case "gender" -> gender = Gender.valueOf(childNodeOfStudentTag.getTextContent());
                            }
                        }
                    }
                    Students.add(new Student(app_id, name, age, emailAddress, gender));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Students;
    }
    
}
