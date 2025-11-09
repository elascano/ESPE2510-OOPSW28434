package ec.edu.espe.contacts.view;
import ec.edu.espe.contacts.model.Contact;

/**
 *
 * @author Mikael Hidalgo, Object Masters, @ESPE
 */
public class ContactsBookApp {
    
    public static void main(String[] args) {
    int id;
    String fullName;
    String phoneNumber;
    int type; //1 = family, 2 = friend, 3 = job mate, 4 = class mate, 5 = unknowm
    String email;
    boolean favorite;
    String instagramId;
    Contact contact;
    
    
    //TODO reading from keyboard
    id = 1;
    fullName = "Mikael Hidalgo";
    phoneNumber = "09898657382";
    type = 1;
    email = "mikaelhidalgo@espe.edu.ec";
    favorite = true;
    instagramId = "@mikaelhidalgo2006";
    
    contact = new Contact(id, fullName, phoneNumber, type, email, favorite, instagramId);
    
    System.out.println("----My contacts----");
    System.out.println("---- id --- fullName --- phoneNumber --- type --- email --- favorite --- instagramId ---");
    System.out.println(contact);
    
    //System.out.println("id before calling the method changeId()-->" + id);
    changeId(id);
    //System.out.println("id after calling teh method changeId() -->" + id);
    
    //System.out.println("id before calling the method changeContact()-->" + contact);
    changeContact(contact);
    //System.out.println("id after calling the method changeContact() -->" + contact);
    
    }
    
    public static void changeId(int localId){
        localId = 100;        
    }
    
    public static void changeContact(Contact contact){
        
        contact = new Contact(2, "mikael Lara", "0878783i30", 3, "mikaelhidalgo.com", false, "hola674");
        System.out.println("contact inside the method is:"+ contact);
        
    }
}
