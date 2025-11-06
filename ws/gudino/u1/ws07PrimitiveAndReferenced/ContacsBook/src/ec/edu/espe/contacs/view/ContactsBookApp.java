package ec.edu.espe.contacs.view;

import ec.edu.espe.contacs.model.Contact;


/**
 *
 * @author Bryan Gudino, KNOWLEDGE ECAPSULED, @ESPE
 */
public class ContactsBookApp {

    public static void main(String[] args) {
    int id;
    String fullName;
    String phoneNumber;
    //a better solution should be using ENUMS, other solution CONSTANT
    int type; // 1=family, 2 =friend, 3= job mate, 4=class mate, 5 unknown
    String email;
    boolean favorite;
    String instagramId;
    Contact contact;
    
    //TODO: reading from keyboard,entrada por teclado
    id=1;
    fullName = "Bryan Gudino";
    phoneNumber = "0961195050";
    type = 1;
    email = "bsgudino@espe.edu.ec";
    favorite = true;
    instagramId = "bryanstng";
    
    contact = new Contact(id, fullName, phoneNumber, type, email, favorite, instagramId);
    
        System.out.println("-------My contacts --------");
        System.out.println("----id -- fullName -- phoneNumber-type-email-favorite-instagramId");
        
        System.out.println(contact);
        
        System.out.println("id before calling the method changeId()-->" + id);
        changeId(id);
        System.out.println("id after calling the method changeId()-->" + id);
        
        System.out.println("contact before calling the method changeContact()-->" + contact);
        changeContact(contact);
        System.out.println("contact after calling the method changeContact()-->" + contact);
        
        
    }
    
    public static void changeId(int localId){
    localId = 100;
    }
    
    public static void changeContact(Contact contact){
    
        contact = new Contact(2, "Jorge Lascano", "0999999999", 3, "invent@gmail.com", false,"jorge");
        System.out.println("contact inside the method" + contact);
    }
    
}
