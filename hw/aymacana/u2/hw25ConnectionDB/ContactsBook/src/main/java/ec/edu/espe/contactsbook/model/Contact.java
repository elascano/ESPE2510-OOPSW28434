package ec.edu.espe.contactsbook.model;

import java.util.ArrayList;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class Contact {
    private int id;
    private String firtName;
    private String lastName;
    private int age;
    private String typeOfContact; //Familty, Friend, Job, Unknown
    private String sex; //Male, Female
    private ArrayList<String> hobbies;
    private String comments;

    public Contact(int id, String firtName, String lastName, int age, String typeOfContact, String sex, ArrayList<String> hobbies, String comments) {
        this.id = id;
        this.firtName = firtName;
        this.lastName = lastName;
        this.age = age;
        this.typeOfContact = typeOfContact;
        this.sex = sex;
        this.hobbies = hobbies;
        this.comments = comments;
    }
<<<<<<< HEAD
=======

    @Override
    public String toString() {
        return "Contact:" + "\nId: " + id + "\nFirtName: " + firtName + "\nLastName: " + lastName 
                + "\nAge: " + age + "\nTypeOfContact: " + typeOfContact + "\nSex: " + sex + "\nHobbies:" + hobbies + "\nComments=" + comments + '}';
    }
    
    
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
    
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
     * @return the firtName
     */
    public String getFirtName() {
        return firtName;
    }

    /**
     * @param firtName the firtName to set
     */
    public void setFirtName(String firtName) {
        this.firtName = firtName;
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
    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    /**
     * @param hobbies the hobbies to set
     */
    public void setHobbies(ArrayList<String> hobbies) {
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
