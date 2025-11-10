 package ec.edu.espe.contacts.view;

import ec.edu.espe.contacts.model.Contact;

/**
 *
 * @author Josue Carvajal, THE ART OF PROGRAMMING, @ESPE
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
        fullName= "Josue Carvajal";
        phoneNumber="0995806788";
        type=1;
        email="josuebgabriel@gmail.com";
        favorite = true;
        instagramId ="Josue_Carvajal";
        
      
       
        
        contact = new Contact (id, fullName, phoneNumber, type, email, favorite, instagramId);
        
        System.out.println("-----My Contacts-----");
        System.out.println("------- id ------- fullName -- phoneNumber-----type------email ----------------------------- favorite -- instagramId");
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
        contact = new Contact(2, "Gabriel Carvajal", "0999999999", 0, "josue@gmail.com", true, "Gabriel_");
        System.out.println("conctact inside the method " + contact);
    }
    
    
}
