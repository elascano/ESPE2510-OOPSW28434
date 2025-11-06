package ec.edu.espe.contact.view;
import ec.edu.espe.contact.model.Contact;
/**
 *
 * @author Mathews Pastor, Poower Rangers of Programing, @ESPE
 */
public class ContactsBookApp {
    public static void main(String[] args) {
        int id;
        String fullName;
        String phoneNumber;
        int type; //1 = family, 2 = friend, 3 = job mate, 4 = class mat, 5 = unkown
        String email;
        boolean favorite;
        String instagramId;
        Contact contact;

        //TODO: reading from keyborad
        id = 1;
        fullName = "Mathews Pastor";
        phoneNumber = "0983489630";
        type = 1;
        email = "mspastor.h@gmail.com";
        favorite = true;
        instagramId = "mthwssd";

        contact = new Contact(id, fullName, phoneNumber, type, favorite, email, instagramId);
        System.out.println("---- My contacts ----");
        System.out.println("----- id ----- fullName ----- phoneNumber --- type ----- email ---------- favorite --------- instagramId -----");
        System.out.println(contact);
        
        System.out.println("id before calling the method changeId --> " + id);
        changeId(id);
        System.out.println("id after calling the method changeId --> " + id);
        
        System.out.println("Contact before calling the method changeContact() --> " + contact);
        changeContact(contact);
        System.out.println("Contact after calling the method changeContact() --> " + contact);
    }
    
    public static void changeId(int localId){
        localId = 100;
    }
    
    
    public static void changeContact(Contact contact){
        contact = new Contact(2, "Said Hernandez", "09999999999", 4, false, "pastorsaidm@gmail.com", "mspastor");
        System.out.println("contact inside the method " + contact);
    }
    
}
