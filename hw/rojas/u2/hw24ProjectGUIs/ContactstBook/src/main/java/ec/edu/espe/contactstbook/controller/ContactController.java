package ec.edu.espe.contactstbook.controller;

import ec.edu.espe.contactstbook.model.Contact;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Josue Rojas
 */
public class ContactController {

    private ContactDAO dao = new ContactDAO();
    
    public boolean addContact(int id, String firstName, String lastName, Date birthDate, int age, String typeOfContact, String sex, ArrayList<String> hobbies, String comments){
        Contact contact = new Contact(id, firstName, lastName, birthDate, age, typeOfContact, sex, hobbies, comments);
        return dao.save(contact);
    }
    
}
