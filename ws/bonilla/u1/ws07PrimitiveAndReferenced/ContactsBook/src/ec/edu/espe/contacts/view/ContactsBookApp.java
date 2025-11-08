package ec.edu.espe.contacts.view;

import ec.edu.espe.contacts.model.Contact;

/**
 *
 * @author Arelis Bonilla, Student, @ESPE
 */
public class ContactsBookApp {
    public static void main(String[] args) {
        int id;
        String fullName;
        String phoneNumber;
        int type; //1 = family, 2 = friend, 3 = job mate, 4 = class mate, 5 unknown}
        String email;
        boolean favorite;
        String instagramId;
        Contact contact;
        
        //T0D0: reading form keybord
        id = 1;
        fullName = "Samantha Bonilla";
        phoneNumber = "0996171265";
        type = 1;
        email = "asbonilla7@espe.edu.ec";
        favorite = true;
        instagramId = "sami_a.b.c";
        
        contact = new Contact(id, fullName, phoneNumber, type, email, favorite, instagramId);
        
        System.out.println("----My contacts----");
        System.out.println("----------- id --- fullName --- phoneNumber -- type ---------- email -------- favorite --- instagramId");
        
        System.out.println(contact);
        
        System.out.println("id before calling the method changeID --> " + id);
        changeId(id);
        System.out.println("id after calling the method changeID --> " + id);
        
        System.out.println("id before calling the method changeContact --> " + contact);
        changeContact(contact);
        System.out.println("id after calling the method changeContact --> " + contact);
        
        
    }
    
    public static void changeId(int localId){
        localId = 100;
    }
    
    public static void changeContact(Contact localContact){
        localContact = new Contact(2, "Samantha Bonilla", "0996171265", 2, "asbonilla7@espe.edu.ec", false, "sami_a.b.c");
        System.out.println("contact inside the method " + localContact);
    }
    
}