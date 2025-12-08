package ec.edu.espe.contactsbook.controller;

import ec.edu.espe.contactsbook.model.Contact;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author Emily Calle, @ESPE
 */
public class ContactController {

    private final MongoCollection<Document> contactCollection;
    private static final String COLLECTION_NAME = "Contacts"; 

  
    public ContactController() {
        MongoDatabase db = MongoConnectionManager.getDatabase();
        if (db != null) {
            this.contactCollection = db.getCollection(COLLECTION_NAME);
        } else {
            this.contactCollection = null;
            System.err.println("ERROR: No se pudo conectar a la base de datos MongoDB.");
        }
    }

   
    public boolean create(Contact contact) {
        if (contactCollection == null) {
            System.err.println("Colección no disponible. No se puede guardar el contacto.");
            return false;
        }

        try {
            Document document = new Document()
                    .append("id", contact.getId())
                    .append("firstName", contact.getFirstName())
                    .append("lastName", contact.getLastName())
                    .append("age", contact.getAge())
                    .append("typeOfContact", contact.getTypeOfContact())
                    .append("sex", contact.getSex())
                    .append("hobbies", contact.getHobbies())
                    .append("comments", contact.getComments());

            contactCollection.insertOne(document);
            System.out.println("Contacto con ID " + contact.getId() + " guardado en MongoDB exitosamente.");
            return true;
        } catch (Exception e) {
            System.err.println("Error al insertar el contacto en MongoDB: " + e.getMessage());
            return false;
        }
    }
    

}