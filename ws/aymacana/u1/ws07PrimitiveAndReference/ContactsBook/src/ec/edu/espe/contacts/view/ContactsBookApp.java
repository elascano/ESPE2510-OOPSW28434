package ec.edu.espe.contacts.view;

import ec.edu.espe.contacts.model.Contact;

/**
 *
 * @author Mateo Aymacaña, T.A.P.(The Art of Programming), @ESPE
 */
public class ContactsBookApp {

    public static void main(String[] args) {
        int id;
        String fullName;
        String phoneNumber;
        int type; //1=family, 2 = friend, 3 = job mate, 4 = class mate, 5 = unknown
        String email;
        boolean favorite;
        String instagramId;
        Contact contact;
        
        //TODO:readin form keyboard
        id = 1;
        fullName = "Mateo Aymacaña";
        phoneNumber = "0992829099";
        type = 1;
        email = "mateo.oso182@gmail.com";
        favorite = true;
        instagramId = "Matt_Oso182";
        
        contact = new Contact(id, fullName, phoneNumber, type, email, favorite, instagramId);
        
        System.out.println("-----My contacs-----");
        System.out.println("-id------Full Name---Phone Number---Type------------Email--------------Favorite-----Instagram Id-");
        
        System.out.println(contact);
        
        System.out.println("id before caling the method changedId ---> " + id);
        changeId(id);
        System.out.println("id after calling the method changedId ---> " + id);
        
        System.out.println("contact before caling the method changedContact ---> " + contact);
        changedContact(contact);
        System.out.println("contact after calling the method changedContact ---> " + contact);
        
    }
    
    public static void changeId(int localid){
        localid = 100;
    }
    
    public static void changedContact(Contact localContact){
        localContact = new Contact(2, "Mateo Osorio", "03147852369", 3, "mfaymacana@espe.edu.ec", false, "50Sukres");
        System.out.println("Contact inside the method ---> " + localContact);
    }
    
}
