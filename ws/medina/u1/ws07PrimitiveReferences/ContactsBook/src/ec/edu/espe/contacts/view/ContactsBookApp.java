package ec.edu.espe.contacts.view;
import ec.edu.espe.contacts.model.Contact;

/**
 *
 * @author Joseph Medina
 */
public class ContactsBookApp {
    
    public static void main(String[] args) {
    int id;
    String fullNmae;
    String phoneNumber;
    int type; //1 = family, 2=friend, 3 = job mate, 4 = class mate, 5 unknown
    String email;
    boolean favorite;
    String instagramId;
    
    Contact contact;
    
    //TODO: readig from keyboard
    id = 1;
    fullNmae = "Joseph Medina";
    phoneNumber = "0963514785";
    type = 1;
    email = "joseph3elcrac@gmail.com";
    favorite = true;
    instagramId = "joseph_bms";
    
    contact = new Contact(id, fullNmae, phoneNumber, type, email, favorite, instagramId);
    
        System.out.println("==== My contacts ====");
        System.out.println("----id----" +  "----fullNmae----" + ",---- phoneNumber----" + "---- type---" + type + "----email----" + "----favorite----" +  "----instagramId----");
    
    System.out.println(contact);
    
        System.out.println("id before calling the method change ID() --> " + id);
        changeId(id);
        System.out.println("id after calling the method change ID() --> " + id);
        
        System.out.println("id before calling the method changeContact() --> " + contact);
        changeContact(contact);
        System.out.println("id after calling the method changeContact() --> " + contact);
        
    
    }
    
    
    public static void changeId (int localId){
        localId = 100;
    }
    
    public static void changeContact (Contact contact){
        contact = new Contact (12, "Joseph Medina ", "0963514785", 3,"joseph3elcrac@gmail.com", true, "josepb_bms");
        System.out.println("contact inside the method " + contact);
        
        
    }
    
}
