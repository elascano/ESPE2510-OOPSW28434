/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.contacts.view;

import ec.edu.espe.contacts.model.Contact;

/**
 *
 * @author Paulo Ramos, Team Neon Cursed, @ESPE
 */
public class ContactsBookApp {

    public static void main(String[] args) {
        int id;
        String fullName;
        String phoneNumber;
        int type; // 1 = family, 2 = friend, 3 = job mate, 4 = class mate        
        String email;
        boolean favorite;  
        String instagramId;
        Contact contact;

        //TODO: reading form keyboard
        id = 1;
        fullName = "Paulo Ramos";
        phoneNumber = "0995785176";
        type = 1;
        email = "paulo.alramos24@gmail.com";
        favorite = true;
        instagramId = "pauloal";

        contact = new Contact(id, fullName, phoneNumber, type, email, favorite, instagramId);
        
        System.out.println("----My contacts are----");
        System.out.println("----- id ----- fullName ---- phoneNumber ---- type ---- email            ---- favorite ----- instagramId");

        System.out.println(contact);
        
        System.out.println("id before calling the method changeID() -->" +id);
        changeId(id);
        System.out.println("id before calling the method changeID() -->" +id);
        
        System.out.println("contact before calling the method changeID() -->" +contact);
        changeContact(contact);
        System.out.println("contact before calling the method changeID() -->" +contact);
        
    }
    
    public static void changeId(int localId){
        localId = 100;
    }
    
    public static void changeContact(Contact contact){
        contact = new Contact (2, "Paulo Alejandro", "0999999999", 3, "paulo@gmail.com", false, "paulo");
        System.out.println("contact inside the method" +contact);
    }
}
