package ec.edu.espe.contacts.view;

import ec.edu.espe.contacts.model.Contact;

/**
 *
 * @author Emily Calle
 */
public class ContactsBookApp {

    public static void main(String[] args) {
        int id;
        String fullName;
        String phoneNumber;
        int type; //1 = family, 2 = friend, 3 = job mate, 4 = class mate, 5 = unknown
        String email;
        boolean favorite;
        String instagramId;
        Contact contact;
        
        //TODO: reading form keyboard
        id = 1;
        fullName = "Emily Calle";
        phoneNumber = "0962769840";
        type = 1;
        email = "emily.scarleth444@gmail.com";
        favorite = true;
        instagramId = "emscavlx";
        
        contact = new Contact(id, fullName, phoneNumber, type, email, favorite, instagramId);
        System.out.println("---My contacts-----");
        System.out.println("-----------id---fullName---phoneNumber---type--------email-----------------favorite--------instagramId");
        
        System.out.println(contact);
        
        System.out.println("id before calling method change id -->" + id);
        changeId(id);
        System.out.println("id after  calling method change id -->" + id);
        
        System.out.println("contact before  calling method changeContact() -->" + contact);
        changeContact(contact);
        System.out.println("contact after  calling method changeContact() -->" + contact);
    
    }
    
    public static void changeId(int LocalId){
    LocalId = 100;
    }
    
    public static void changeContact(Contact contact){
        contact = new Contact(2,"Emily Calle","0962769840", 3,"emily.scarleth444@gmail.com", false,"emscavlx");
        System.out.println("contact inside the method" + contact);
    }
    
}
