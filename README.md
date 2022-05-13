## Methodology of programming  I Project
###Stipendium Hungaricum Scholarship
* This program is a project work for the university
###Purpose of the program:
It's topic in the field of Education this program is registering students in the Stipendium Hungaricum Scholarship\
In order to give the user the ability to decide, which action they want to examine and how many times,\
everything is organized into menus, using the CRUD operations.
### Communication With the User 
The User has to communicate with the program via console (data input and output), manage and store element data in an XML file.

### Functionality
   * I used an ArrayList to create the collection of the student and I declared it in the main function,\
    and I pass my student object to any function that requires it.
   * If the user chooses an option but the xml file is empty Then program displays "There Is No Students!!	Register A Student First"
   * The program only accepts valid choices from the user, both upper and lowercase selections are allowed.
   * If an illegal choice is made, The program displays, " Illegal choice	Try Again!" and the menu options are displayed again.
   * If the user input a string where the program expects an integer the program displays "Must Be An Integer Not Containing Letters"\
    and the program does not crush but ask the user to enter again.
   * The user's age has to be between **18-40**, otherwise the program displays:\
      _"Your Are Not Eligible To Apply For The Stipendium Hungaricum Scholarship\
        Students Between 18-40 Are Eligible To Apply For The Hungaricum Scholarship"_

When you run the program it displays a menu options to the user as follows:

###The menu
_**Welcome To Stipendium Hungaricum Scholarship Application System
Please Select A Menu Point:**_
* R - Register A Student
* L - List All Students
* C - Clear All Students
* S - Search A Student By Its Application ID
* F - Search A Student By Tts Name
* D - Delete A Student By Tts Application ID
* T - Delete A Student By Tts Name
* U - Update A Student By Tts Application ID
* G - Update A Student By Tts Name
* Q - Quit
* Enter your choice:


###I created functions for each menu option.
* When the application finished with a logical part (listing/updating/etc), it displays the MENU again.
* I started by defining a function that displays the menu
###User Enters 'L' or 'l':
   * Using the` listStudent()` method.
   * The program displays all the students in the list.
   * If the list or the xml file is empty The program displays _"There Is No Students!!	Register A Student First"_
   * If the list is not empty then all the students are displayed with their data
###User Enters 'R' or 'r':
   * Using the` RegisterStudent()` method.
   * Firstly The program prompts the user for hom many students he wants to register.
   * Then the program prompts the user for a **"Name, Id, Age, Email address and gender"** of a student to add to the list.
   * If the user enters an application ID that is already added then The program displays _"Student with this application ID is already registered"_

###User Enters 'C' or 'c':
   * Using the` clearStudent()` method.
   * The program confirm if the user wants to clear the list
   * If yes Then the ArrayList of students and the xml file are cleared.
             And the program displays _"The List Of Students Has Been Cleared Successfully...!"_
   * If not the menu function displays.
###User Enters 'S' or 's':
   * Using the` searchStudentByID()` method.
   * The program searches for the student by its app_ID and displays it.
   * If the student is not found then the program displays _"Student With This Application ID Not Found!"_.
###User Enters 'F' or 'f':
   * Using the` searchStudentByName()` method.
   * The program searches for the student by its name and displays it.
   * If the student is not found then the program displays _"Student With This Name Not Found!"_.
###User Enters 'D' or 'd':
   * Using the` deleteStudentByID()` method.
   * The program searches for the student by its app_ID and deletes it.
   * If the student is not found then the program displays _"Student With This app_id Not Found!"_.
###User Enters 'T' or 't':
   * Using the` deleteStudentByName()` method.
   * The program searches for the student by its name and deletes it.
   * If the student is not found then the program displays _"Student With This Name Not Found!"_.
###User Enters 'U' or 'u':
   * Using the` updateStudentByID()` method.
   * The program searches for the student by its app_id to update its Name, Age , Gender and Email Address.
   * If the student is not found then the program displays _"Student With This app_id Not Found!"_.
###User Enters 'G' or 'g':
   * Using the` updateStudentByName()` method.
   * The program searches for the student by its name and updates its Name, Age , Gender and Email Address.
   * If the student is not found then the program displays _"Student With This Name Not Found!"_.
###User Enters 'Q' or 'q':
   * The program displays _"Goodbye"_ and the program terminates.

##Package register:
###Classes:
####Student:
* It has the student object with a name, app_Id, age, email address, gender 
####XmlReader:
* Reads the xml file 
####XmlWriter:
* Stores the data to the xml file
###Enum:
* It has the gender( MALE, FEMALE )
####
> 13 May 2022
>
>**Ibrahim Ibrahim**
>
>_**University of Pécs**_     