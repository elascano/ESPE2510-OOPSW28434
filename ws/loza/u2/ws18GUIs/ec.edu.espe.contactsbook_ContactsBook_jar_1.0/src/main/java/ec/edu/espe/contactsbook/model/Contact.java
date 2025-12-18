package ec.edu.espe.contactsbook.model;
import java.util.ArrayList;
/**
 *
 * @author Steven Loza @Espe
 */
public class Contact {
    private static int counter = 1;  // ID AUTOINCREMENTAL

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String typeOfContact;
    private String sex;
    private ArrayList<String> hobbies;
    private String comments;

    public Contact() {
        this.id = counter++;
    }

    public Contact(String firstName, String lastName, int age, String typeOfContact,
                   String sex, ArrayList<String> hobbies, String comments) {
        this.id = counter++;        // AUTOINCREMENTO
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.typeOfContact = typeOfContact;
        this.sex = sex;
        this.hobbies = hobbies;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "\nid=" + id +
                "\nfirstName=" + firstName +
                "\nlastName=" + lastName +
                "\nage=" + age +
                "\ntypeOfContact=" + typeOfContact +
                "\nsex=" + sex +
                "\nhobbies=" + hobbies +
                "\ncomments=" + comments +
                "\n}";
    }

    // getters y setters...

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Contact.counter = counter;
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
