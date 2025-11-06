/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.contacts.view;

import ec.edu.espe.contacts.model.Contact;

/**
 *
 * @author Steven Loza @ESPE
 */
public class ContactsBookApp {

    public static void main(String[] args) {
        int id;
        String fullName;
        String phoneNumber;
        //a better solution should be using ENUMS, other solution CONTANT
        int type; //1 = family, 2= Friend, 3= job mate, 4= class mate, 5= unknown
        String email;
        boolean favorite;
        String instagramId;
        Contact contact;

        //TODO:reading for keyboard
        id = 1;
        fullName = "Esteban Santiago";
        phoneNumber = "0978945788";
        type = 1;
        email = "esantiago1@espe.edu.ec";
        favorite = true;
        instagramId = "ssloza17";

        contact = new Contact(id, fullName, phoneNumber, type, email, favorite, instagramId);

        System.out.println("....My contacts are.....");
        System.out.println("-----------id -- fullname -- phonenumber -- type -- email                    -- favorite -- instagramId");
        System.out.println(contact);

        System.out.println("id before calling the method changeId() --->" + id);
        changeId(id);
        System.out.println("id after calling the method changeId() --->" + id);
        
        contact.toString();
        
        System.out.println("id before calling the method changeContact() --->" + id);
        changeContact(contact);
        System.out.println("id after calling the method changeContact() --->" + id);
    }

    public static void changeId(int localId) {
        localId = 100;
    }
    public static void changeContact(Contact contact){
        contact = new Contact(2, "Sebas Steven", "0917854967", 3, "ssloza12@espe.edu.ec", false, "sebassty56");
        System.out.println("contact inside the method" + contact);
    }
}
