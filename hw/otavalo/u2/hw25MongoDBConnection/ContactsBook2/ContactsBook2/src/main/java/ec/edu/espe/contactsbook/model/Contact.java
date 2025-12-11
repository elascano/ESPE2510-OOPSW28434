package ec.edu.espe.contactsbook.model;

import java.util.ArrayList;

/**
 *
 * @author Arelys Otavalo, The POOwer Rangers of Programming,@espe
 */
public class Contact {
    int id;
    String firstName;
    String lastName;
    int age;
    String typeOfContact; //Family, Friend,Job,Unknown
    String sex; //male, female
    ArrayList<String> hobbies;
    String comments; 
    
    public Contact(){
        
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", \nfirstName=" + firstName + ", \nlastName=" + lastName + ", \nage=" + age + ", \ntypeOfContact=" + typeOfContact + ", \nsex=" + sex + ", \nhobbies=" + hobbies + ", \ncomments=" + comments + '}';
    }
            

<<<<<<< HEAD
    public Contact(int id, String firstName, String lastName, int age, String typeOfContact, String sex, ArrayList<String> hobbies, String coomments) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.typeOfContact = typeOfContact;
        this.sex = sex;
        this.hobbies = hobbies;
        this.comments = coomments;
    }

=======
    public Contact(int age, String firstName, String lastName,
                   String typeOfContact, String sex,
                   ArrayList<String> hobbies, String comments) {

        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.typeOfContact = typeOfContact;
        this.sex = sex;
        this.hobbies = hobbies;
        this.comments = comments;
    }
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTypeOfContact() {
        return typeOfContact;
    }

    public void setTypeOfContact(String typeOfContact) {
        this.typeOfContact = typeOfContact;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    
}
