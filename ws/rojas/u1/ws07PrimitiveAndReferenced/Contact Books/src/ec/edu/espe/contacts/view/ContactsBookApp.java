package ec.edu.espe.contacts.view;

import ec.edu.espe.contacts.model.Contact;

/**
 *
 * @author Josue Rojas,Team Neon Cursed, @ESPE
 */
public class ContactsBookApp {
    public static void main(String[] args) {
        int id;
        String fullName;
        String phoneNumber;
        int type; //1= family, 2= friend, 3=job mate, 4= class mate
        String email;
        boolean favorite;
        String instagramId;
        Contact contact;
    
        //TODO: reading from keyboard
        id = 1;
        fullName ="Josue Rojas";
        phoneNumber= "0988227314";
        type = 1;
        email = "svp.jd.rojas@gmail.com";
        favorite = true;
        instagramId = "j0sue.xro";
    
        contact = new Contact(id, fullName, phoneNumber, type, email, favorite, instagramId);
            
        System.out.println("-----My contacts -----");
        System.out.println("----------id-----fullName---phoneNumber--type----email-                --favorite----instagramId");
        System.out.println(contact);
        
        
        //System.out.println("id before calling the method changeId()  --->" + id);
        changeId(id);
        //System.out.println("id before calling the method changeId()  --->" + id);
        
        //System.out.println("id before calling the method changeIContact()  --->" + contact);
          changeContact(contact);
        //System.out.println("id after calling the method changeContact()  --->" + contact);
        
    }
    
    public static void changeId(int localId){
        localId= 100;
    }
    
    public static void changeContact(Contact contact){
    
        contact =new Contact(2,"Jorge Lascano","099999999",3,"edi@yahoo.com", true, "jorge");
      
        System.out.println("Contact inside the method " + contact);
    }
    
}
