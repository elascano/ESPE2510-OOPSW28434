/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.contactsbook.model;

import java.util.ArrayList;

/**
 *
 * @author Pablo Collaguazo
 */
public class Contact {
    private int id;
    private String FirstName;
    private String LastName;
    private int age;
    private String TypeOfContact;
    private String sex;
    private ArrayList<String> hobbies;
    private String comments;

    public Contact(int id, String FirstName, String LastName, int age, String TypeOfContact, String sex, ArrayList<String> hobbies, String comments) {
        this.id = id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.age = age;
        this.TypeOfContact = TypeOfContact;
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
     * @return the FirstName
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * @param FirstName the FirstName to set
     */
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    /**
     * @return the LastName
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * @param LastName the LastName to set
     */
    public void setLastName(String LastName) {
        this.LastName = LastName;
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
     * @return the TypeOfContact
     */
    public String getTypeOfContact() {
        return TypeOfContact;
    }

    /**
     * @param TypeOfContact the TypeOfContact to set
     */
    public void setTypeOfContact(String TypeOfContact) {
        this.TypeOfContact = TypeOfContact;
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
