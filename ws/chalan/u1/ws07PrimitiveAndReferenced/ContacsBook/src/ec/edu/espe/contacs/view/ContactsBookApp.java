package ec.edu.espe.contacs.view;

import ec.edu.espe.contacs.model.Contact;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class ContactsBookApp {

    public static void main(String[] args) {

        int id;
        String fullName;
        String phoneNumber;
        int type; // 1=family, 2 =friend, 3= job mate, 4=class mate, 5= unknow
        String email;
        boolean favorite;
        String instagramId;
        Contact contact;

        //TODO: reading from keyboard
        id = 1;
        fullName = "Kevin Chalan";
        phoneNumber = "0983907580";
        type = 1;
        email = "kachala@espe.edu.ec";
        favorite = true;
        instagramId = "kevanthoc";

        contact = new Contact(id, fullName, phoneNumber, type, email, favorite, instagramId);
        System.out.println("---My contacts--");
        System.out.println("\"------------id----- -fullName--- phoneNumber--- ----type-- --- email----- --favorite---- ---instagramId");

        System.out.println(contact);
      //  System.out.println("id before calling the method changeId ---->" + id);
        changeId(id);
      //  System.out.println("id after calling the method changeId ---->" + id);

       // System.out.println("contact before calling the method changeId ---->" + contact);
        changeContact(contact);
       // System.out.println("contact after calling the method changeId ---->" + contact);

    }

    public static void changeId(int localId) {
        localId = 100;
    }

    public static void changeContact(Contact Contact) {
        Contact = new Contact(10, "jorge lascano", "09999999", 3, "edi@yahoo.com", false, "jorge");
       // System.out.println("contact inside teh method" +Contact);
    }

}
