package ec.edu.espe.contactsbook.controller;

import ec.edu.espe.contactsbook.model.Contact;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
public class ContactManager {
private static final String CONNECTION_STRING = "mongodb+srv://Cesar:Cesar2006@cluster0.tgbv2qc.mongodb.net/";
    private static final String DATABASE_NAME = "ContactsBookDB"; 
    private static final String COLLECTION_NAME = "contacts"; 

    private static MongoCollection<Document> getCollection() {
        try {
            ConnectionString connectionString = new ConnectionString(CONNECTION_STRING);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build();
            MongoClient mongoClient = MongoClients.create(settings);
            
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            return database.getCollection(COLLECTION_NAME);
            
        } catch (Exception e) {
            System.err.println("Error de conexión a MongoDB: " + e.getMessage());
            return null;
        }
    }

    private static Document toDocument(Contact contact) {
        Document doc = new Document("id", contact.getId())
                .append("firstName", contact.getFirstName())
                .append("lastName", contact.getLastName())
                .append("age", contact.getAge())
                .append("typeOfContact", contact.getTypeOfContact())
                .append("sex", contact.getSex())
                .append("hobbies", contact.getHobbies() != null ? contact.getHobbies() : new ArrayList<String>())
                .append("comments", contact.getComments());
        return doc;
    }

    public static boolean save(Contact contact) {
        MongoCollection<Document> collection = getCollection();
        if (collection == null) {
            return false;
        }
        
        try {
            Document contactDocument = toDocument(contact);
            collection.insertOne(contactDocument);
            return true;
        } catch (Exception e) {
            System.err.println("Error al insertar documento: " + e.getMessage());
            return false;
        }
    }
}
