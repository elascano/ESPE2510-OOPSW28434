package ec.edu.espe.contacts.view;
import ec.edu.espe.contacts.model.Contact;
/**
 *
 * @author Arelys Otavalo, The POOwer Rangers of Programming, @ESPE
 */
public class ContactsBookApp {
    
    public static void main(String[] args) {
        int id;
        String fullName;
        String phoneNumber = null;
        int type; // 1= family, 2= friend, 3=job mate, 4= class mate, 5= unknown
        String email;
        boolean favorite;
        String instagramId;
        Contact contact;
        
        //TODO: Reading from keyboard
        id=1;
        fullName = "Arelys Otavalo"; 
        phoneNumber = "0999986553";
        type=1;
        email= "amotavalo@espe.edu.ec";
        favorite=true;
        instagramId ="arelys_m";
        
        contact = new Contact(id, fullName, phoneNumber, type, email, favorite, instagramId);
        System.out.println("-----My contacts----");
        System.out.println("---------id-------fullNmae------phoneNumber----type----------email----------favorite-------instagramID");
        System.out.println(contact);
        
        System.out.println("id before calling the method changeId -->" + id);
        changeId(id);
        System.out.println("id after calling the method changeId -->" + id);
        
        System.out.println("contact before calling the method changeContact -->" + contact);
        changeContact(contact);
        System.out.println("Contact after calling the method changeId -->" + contact);
     
    }    
    
     public static void changeId(int localId){
         localId = 1000;
     }
    
     public static void changeContact(Contact contact){
        contact = new Contact(2, "Arelys Otavalo", "0999986553", 3, "amotavalo@espe.ec", false, "are");
         System.out.println("Conact inside the method -->" + contact);
     }
}
