/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.contacts.view;

import ec.edu.espe.contacts.model.Contact;

/**
 *
 * @author Daniel Chicaiza, Object Masters, @ESPE
 */
public class ContactsBookApp {
    public static void main(String[] args) {
        int id;
        String fullName;
        String phoneNumber;
        int type; //1 = family , 2 = friend , 2 = job mate , 4 = class mate , 5 unknown
        String email;
        boolean favorite;
        String instagramId ;
        Contact contact;
        
      
        
        
        //TODO: reading from keybroard
        id=1;
        fullName= "Daniel Chicaiza";
        phoneNumber="0979317376";
        type=1;
        email="xxfernan6262ss@gmail.com";
        favorite = true;
        instagramId ="fern4ndo_dani3l";
        
      
       
        
        contact = new Contact (id, fullName, phoneNumber, type, email, favorite, instagramId);
        
        System.out.println("-----My Contacts-----");
        System.out.println("------- id -- fullName -- phoneNumber-type - email ----------------------------- favorite -- instagramId");
        System.out.println(contact);
        
        
        System.out.println("id before calling the method changeId() --> "+ id );
        
        changeId(id);
        
        System.out.println("Contact after calling the method cchangeContact() --> "+ id );
        
        
        System.out.println("Contact before calling the method changeContact() --> "+ contact );
        
        changeContact(contact);
        
        System.out.println("id after calling the method changeId() --> "+ contact );
        
    }
    
    public static void changeId(int localId){
        localId = 100;
        
    }
    
    public static void changeContact(Contact contact){
        contact = new Contact(2, "David Chicaiza", "0990791818", 0, "davidchicaiza@gmail.com", true, "davidex");
        System.out.println("conctact inside the method " + contact);
    }
    
    
}
