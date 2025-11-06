
package ec.edu.espe.contacts.view;

import ec.edu.espe.contacts.model.Contact;

/**
 *
 * @author Thais Santórum Team 6 - Paradigm, @ESPE
 */
public class ContactBookApp {
    public static void main(String[] args) {
    int id;
    String fullName;
    String phoneNumber;
    int type; // 1 = family  2 = friend   3 = job mate   4 = class mate, 5 unknown
    String email;
    boolean favorite;
    String instagramId;
    Contact contact;
    
    
    // TODO reading keyboard
    id = 1;
    fullName = "Thais Yetsalem Santórum Sandoval";
    type = 1;
    phoneNumber = "0984564638";
    email = "santorumthais@mail.com";
    favorite = true;
    instagramId = "lanabanana";
       
    contact = new Contact(id, fullName, phoneNumber, type, email, favorite, instagramId);
    
    System.out.println("- - - My contacts are -  - -");
    System.out.println(contact);
    
    
    System.out.println("\n\nId before calling the method changeId()  =  " + id);
    changeId(id);
    System.out.println("Id after calling the method changeId()  =  " + id);
    
    
    System.out.println("\n\nId before calling the method changeId()  =  \n" + contact);
    changeContact(contact);
    System.out.println("\n\nId after calling the method changeId()  =  \n" + contact);

    }
    
    public static void changeId(int id){
        id = 100;
    }
    
    public static void changeContact(Contact contact){
        contact = new Contact(2, "Thais Santórum", "0999999999", 1, "santorumth@gmail.com", false, "nunu");
        System.out.println("\n - . . Contact inside the method \n" + contact);
    }
}
