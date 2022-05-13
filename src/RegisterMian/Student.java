package RegisterMian;

import java.io.Serializable;

public class Student implements Serializable {//to deal with runtime error
    private int app_id; //each student will have an ID, name and age
    private String name;
    private int age;
    private String emailAddress;
    private final Gender gender;

    public Student() {
        this(0 ,  "",  0, "");
    }

    public Student(int app_id, String name, int age, String emailAddress){ //a parameterized  constructor to egt the input from the user
        this.app_id = app_id; // initializing my instance variables
        this.name = name;
        this.age = age;
        this.emailAddress = emailAddress;
        gender= Gender.MALE;
    }

    public Student(int app_id, String name, int age, String emailAddress, Gender gender){ //a parameterized  constructor to egt the input from the user
        this.app_id = app_id; // initializing my instance variables
        this.name = name;
        this.age = age;
        this.emailAddress = emailAddress;
        this.gender = gender;
    }

    //Implement a setter and a getter to each attribute.

    public int getApp_id(){
        return app_id;
    }
    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge(){
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public Gender getGender() {
        return gender;
    }
    @Override
    //* Implement a constructor.
    public String toString(){ // a string method to display the details of the student
        return name+"\t\t"+app_id+"\t\t"+age+"\t\t"+emailAddress+"\t\t"+gender;
    }


}