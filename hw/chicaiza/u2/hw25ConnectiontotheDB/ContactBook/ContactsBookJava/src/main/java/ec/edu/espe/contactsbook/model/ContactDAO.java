package ec.edu.espe.contactsbook.model;

import utils.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author Mathews
 */
public class ContactDAO {

    private final MongoCollection<Document> collection;

    public ContactDAO() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        collection = database.getCollection("contacts");
    }

    // ==========================================
    // MÉTODO PARA GUARDAR CONTACTO EN LA NUBE
    // ==========================================
    public boolean save(Contact contact) {
        try {
            Document doc = new Document("id", contact.getId())
                    .append("firstName", contact.getFirstName())
                    .append("lastName", contact.getLastName())
                    .append("age", contact.getAge())
                    .append("typeOfContact", contact.getTypeOfContact())
                    .append("sex", contact.getSex())
                    .append("hobbies", contact.getHobbies())
                    .append("comments", contact.getComments());

            collection.insertOne(doc);
            System.out.println("Contacto guardado con éxito en MongoDB.");
            return true;

        } catch (Exception e) {
            System.out.println("Error al guardar el contacto: " + e.getMessage());
            return false;
        }
    }
}
