package ec.edu.espe.contactsbook.model;

import java.util.ArrayList;

public class Contact {

    private int age;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String typeOfContact;
    private String sex;
    private ArrayList<String> hobbies;
    private String comments;

    public Contact() {
    }

    public Contact(int age, String firstName, String lastName, String birthDate,
                   String typeOfContact, String sex, ArrayList<String> hobbies, String comments) {

        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.typeOfContact = typeOfContact;
        this.sex = sex;
        this.hobbies = hobbies;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", typeOfContact='" + typeOfContact + '\'' +
                ", sex='" + sex + '\'' +
                ", hobbies=" + hobbies +
                ", comments='" + comments + '\'' +
                '}';
    }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getTypeOfContact() { return typeOfContact; }
    public void setTypeOfContact(String typeOfContact) { this.typeOfContact = typeOfContact; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public ArrayList<String> getHobbies() { return hobbies; }
    public void setHobbies(ArrayList<String> hobbies) { this.hobbies = hobbies; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
}
