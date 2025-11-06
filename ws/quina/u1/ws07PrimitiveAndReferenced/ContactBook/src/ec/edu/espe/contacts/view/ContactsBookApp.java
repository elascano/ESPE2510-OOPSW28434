package ec.edu.espe.contacts.view;
import ec.edu.espe.contacts.model.Contact;

/**
 *
 * @author Maryuri Quina, @ESPE
 */
public class ContactsBookApp {
    public static void main(String[] args) {
        int id;
        String fullName;
        String phoneNumber;
        int type; //1=family, 2=friend, 3=job mate, 4=class mate, 5 =unknown
        String email;
        boolean favorite;
        String instagramId;
        Contact contact;
        
        //T0D0: reading form keyboard
        id = 1;
        fullName = "Maryuri Quina";
        phoneNumber = "0985876424";
        type = 1;
        email = "maryurisalome3@gmail.com";
        favorite = true;
        instagramId = "maryurisalome";
        
        contact = new Contact(id, fullName, phoneNumber, type, email, favorite, instagramId);
        System.out.println("---- My contacts ----");
        System.out.println("--------- id -- fullName -- phoneNumber-type -- email -------------- favorite -- instagramId");
        
        System.out.println(contact);
        
        System.out.println("id before calling the method changeId --> " + id);
        changeId(id);
        System.out.println("id after calling the method changeId --> " + id);
        
        System.out.println("contact before calling the method changeId --> " + contact);
        changeContact(contact);
        System.out.println("contact after calling the method changeId --> " + contact);
    }
    
    public static void changeId(int localId){
        localId = 100;
    }
    
    public static void changeContact(Contact contact){
        contact = new Contact(2, "Maryuri Quina", "0985876424", 3, "maryurisalome3@gmail.com", true, "maryurisalome");
        System.out.println("contact inside the method" + contact);
    }
}
