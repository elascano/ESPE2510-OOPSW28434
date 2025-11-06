
package ec.edu.espe.contacts.view;

import ec.edu.espe.contacts.model.Contact;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
public class ContactBookApp {
    public static void main(String[] args) {
     int id;
     String fullName;
     String phoneNumber;
    // a better solution should be using ENUMS, other solution CONSTANT
     int type; //1 = family, 2 = friend, 3 = job mate, 4 = class mate, 5 = unknown
     String email;
     boolean favorite;
     String instagramId;
     Contact contact;
     
     //TODO reading from keyboard
     id = 1;
     fullName = "Cesar Vargas";
     phoneNumber = "0961195050";
     type = 1;
     email = "cesar.06vx@gmail.com";
     favorite = true;
     instagramId = "cesar.06x";
     
     contact = new Contact(id, fullName, phoneNumber, type, email, favorite, instagramId);
     System.out.println("---- My Contacts ----");
     System.out.println("--------id --- fullName -- phoneNumber - type - email              -- favorite --   instagramId");
     System.out.println(contact);
     
     System.out.println("id before calling the method changeId() --> " + id);
     changeId(id);
     System.out.println("id after calling the method changeId() --> " + id);
     
     System.out.println("Contact before calling the method changeContact() --> " + contact);
     changeContact(contact);
     System.out.println("Contact after calling the method changeContact() --> " + contact);
     
        
    }
    
    public static void changeId(int localId){
        localId = 100;
    }
    public static void changeContact(Contact localContact){
        localContact = new Contact(2, "Yussepe", "099 666 3456", 3,"yussepe@gmail.com" , false, "Yussepesinho");
        System.out.println("contact inside the method" + localContact );
    }
    
}
