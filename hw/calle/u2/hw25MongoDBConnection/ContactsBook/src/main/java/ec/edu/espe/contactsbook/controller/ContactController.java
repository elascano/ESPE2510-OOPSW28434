package ec.edu.espe.contactsbook.controller;

import ec.edu.espe.contactsbook.model.Contact;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ContactController {

    private final MongoCollection<Document> contactCollection;
    private static final String COLLECTION_NAME = "Contacts"; 
    
    public ContactController() {
        MongoDatabase db = MongoConnectionManager.getDatabase();
        if (db != null) {
            this.contactCollection = db.getCollection(COLLECTION_NAME);
        } else {
            this.contactCollection = null;
        }
    }

    
    public boolean create(Contact contact) {
        if (contactCollection == null) {
            System.err.println("Collection not available. Cannot save contact.");
            return false;
        }

        try {
            Document document = new Document()
                    .append("id", contact.getId())
                    .append("firstName", contact.getFirstName())
                    .append("lastName", contact.getLastName())
                    .append("age", contact.getAge())
                    .append("birthDate", contact.getBirthDate()) // Campo añadido
                    .append("typeOfContact", contact.getTypeOfContact())
                    .append("sex", contact.getSex())
                    .append("hobbies", contact.getHobbies())
                    .append("comments", contact.getComments());

            contactCollection.insertOne(document);
            System.out.println("Contact with ID " + contact.getId() + " successfully saved to MongoDB.");
            return true;
        } catch (Exception e) {
            System.err.println("Error inserting contact into MongoDB: " + e.getMessage());
            return false;
        }
    }
    
}