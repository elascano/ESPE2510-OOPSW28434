package ec.edu.espe.contactsbook.controller;

import ec.edu.espe.contactsbook.model.Contact;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Paulo Ramos
 */
public class ContactController {

    private ContactDAO dao = new ContactDAO();
    
    public boolean addContact(int id, String firstName, String lastName, Date birthDate, int age, String typeOfContact, String sex, ArrayList<String> hobbies, String comments){
        Contact contact = new Contact(id, firstName, lastName, birthDate, age, typeOfContact, sex, hobbies, comments);
        return dao.save(contact);
    }
   
}