package ec.edu.espe.contacts.view;
import ec.edu.espe.contacts.model.Contact;

/**
 *
 * @author Mateo Cevallos, Object Masters, @ESPE
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
    fullName = "Mateo Cevallos";
    phoneNumber = "09898656656";
    type = 1;
    email = "mdcevallos10@espe.edu.ec";
    favorite = true;
    instagramId = "@mateocevallos2006";
    
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
        
        contact = new Contact(2, "David Paguay", "08787878", 3, "cevmatt@gmail.com", false, "hola124");
        System.out.println("contact inside the method is:"+ contact);
        
    }
}
