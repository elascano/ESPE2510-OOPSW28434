package ec.edu.espe.contactsbook.model;

import java.util.ArrayList;
import org.bson.Document;
/**
 *
 * @author Maryuri Quina, @ESPE
 */
public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String typeOfContact;//Family, Friend Job, Unknown
    private String sex; //male, female
    private ArrayList <String> hobbies;
    private String comments;
    
    public Contact(){
        
    }

    public Contact(int id, String firstName, String lastName, int age, String typeOfContact, String sex, ArrayList<String> hobbies, String comments) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.typeOfContact = typeOfContact;
        this.sex = sex;
        this.hobbies = hobbies;
        this.comments = comments;
    }

    public Document toDocument() {
        Document doc = new Document("id", id) 
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("age", age)
                .append("typeOfContact", typeOfContact)
                .append("sex", sex)
                .append("hobbies", hobbies)
                .append("comments", comments);
        return doc;
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + "\n firstName=" + firstName + "\n lastName=" + lastName + "\n age=" + age + "\n typeOfContact=" + typeOfContact + "\n sex=" + sex + "\n hobbies=" + hobbies + "\n comments=" + comments + '}';
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the typeOfContact
     */
    public String getTypeOfContact() {
        return typeOfContact;
    }

    /**
     * @param typeOfContact the typeOfContact to set
     */
    public void setTypeOfContact(String typeOfContact) {
        this.typeOfContact = typeOfContact;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the hobbies
     */
    public ArrayList <String> getHobbies() {
        return hobbies;
    }

    /**
     * @param hobbies the hobbies to set
     */
    public void setHobbies(ArrayList <String> hobbies) {
        this.hobbies = hobbies;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
}