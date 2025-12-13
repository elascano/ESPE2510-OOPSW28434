package ec.edu.espe.contactsbook.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Emily Calle, @ESPE
 */
public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private int age ;
    private Date birthDate;
    private String typeOfContact;//Family, Friend Job, Unknown
    private String sex; //male, female
    private ArrayList <String> hobbies;
    private String comments;
    
    public Contact(){
        
    }

    @Override
public String toString() {
        return "Contact{\n" +
                "  id=" + id + ",\n" +
                "  firstName='" + firstName + "',\n" +
                "  lastName='" + lastName + "',\n" +
                "  age=" + age + ",\n" +
                "  birthDate='" + birthDate + "',\n" +
                "  typeOfContact='" + typeOfContact + "',\n" +
                "  sex='" + sex + "',\n" +
                "  hobbies=" + hobbies + ",\n" +
                "  comments='" + comments + "'\n" +
                "}";
    }

    
   public Contact(int id, String firstName, String lastName, int age, Date birthDate, String typeOfContact, String sex, ArrayList<String> hobbies, String comments) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
        this.typeOfContact = typeOfContact;
        this.sex = sex;
        this.hobbies = hobbies;
        this.comments = comments;
    }

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
