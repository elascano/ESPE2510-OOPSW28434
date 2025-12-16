
package ec.edu.espe.contactsbook.model;

import java.util.ArrayList;

/**
 *
 * @author Bryan Gudino
 */
public class Contact {
    private int id;
    private String firtsName;
    private String lastName;
    private int age;
    private String typeOfContact; //FAmily,Friend,Job,Unknown
    private String sex; //male, female
    private ArrayList<String>hobbies;
    private String comments;

    public Contact(int id, String firtsName, String lastName, int age, String typeOfContact, String sex, ArrayList<String> hobbies, String comments) {
        this.id = id;
        this.firtsName = firtsName;
        this.lastName = lastName;
        this.age = age;
        this.typeOfContact = typeOfContact;
        this.sex = sex;
        this.hobbies = hobbies;
        this.comments = comments;
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
     * @return the firtsName
     */
    public String getFirtsName() {
        return firtsName;
    }

    /**
     * @param firtsName the firtsName to set
     */
    public void setFirtsName(String firtsName) {
        this.firtsName = firtsName;
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
