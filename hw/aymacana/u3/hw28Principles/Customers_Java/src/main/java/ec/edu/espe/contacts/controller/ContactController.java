package ec.edu.espe.contacts.controller;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */

import ec.edu.espe.contacts.model.Contact;
import ec.edu.espe.contacts.utils.ContactRepository;
import java.util.List;

public class ContactController {
    private final ContactRepository contactRepository;
    
    public ContactController() {
        this.contactRepository = new ContactRepository();
    }
    
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
    
    public Contact getContactById(int id) {
        return contactRepository.findById(id);
    }
    
    public String getDatabaseInfo() {
        return "Database: oop | Collection: Customers";
    }
}
