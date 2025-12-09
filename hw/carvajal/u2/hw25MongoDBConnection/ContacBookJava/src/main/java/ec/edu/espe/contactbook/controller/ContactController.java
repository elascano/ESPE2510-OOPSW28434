package ec.edu.espe.contactbook.controller;

/**
 *
 * @author Josue Carvajal, THE ART OF PROGRAMMING, @ESPE
 */

import ec.edu.espe.contactbook.data.MongoConnection;
import ec.edu.espe.contactbook.model.Contact;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


public class ContactController {

    private final MongoCollection<Document> collection;

    public ContactController() {
        MongoDatabase db = MongoConnection.getDatabase();
        collection = db.getCollection(MongoConnection.COLLECTION_NAME);
    }

    private Document toDocument(Contact c) {
        Document doc = new Document("id", c.getId())
                .append("firstName", c.getFirstName())
                .append("lastName", c.getLastName())
                .append("birthDate", (c.getBirthDate() != null) ? c.getBirthDate().toString() : null)
                .append("typeOfContact", c.getTypeOfContact())
                .append("sex", c.getSex())
                .append("hobbies", c.getHobbies())
                .append("comments", c.getComments());
        return doc;
    }

    private Contact fromDocument(Document d) {
        if (d == null) return null;
        List<String> hobbies = d.getList("hobbies", String.class);
        if (hobbies == null) hobbies = new ArrayList<>();
        String birth = d.getString("birthDate"); // stored as "yyyy-MM-dd"
        LocalDate birthDate = null;
        if (birth != null && !birth.isEmpty()) {
            birthDate = LocalDate.parse(birth);
        }
        Contact c = new Contact(
                d.getInteger("id", 0),
                d.getString("firstName"),
                d.getString("lastName"),
                birthDate,
                d.getString("typeOfContact"),
                d.getString("sex"),
                hobbies,
                d.getString("comments")
        );
        return c;
    }

    // CREATE
    public void saveContact(Contact contact) {
        Document existing = collection.find(eq("id", contact.getId())).first();
        if (existing != null) {
            throw new IllegalArgumentException("Ya existe un contacto con el ID: " + contact.getId());
        }
        collection.insertOne(toDocument(contact));
    }

    // READ by id
    public Contact findById(int id) {
        Document d = collection.find(eq("id", id)).first();
        return fromDocument(d);
    }

    // READ all
    public List<Contact> findAll() {
        List<Contact> list = new ArrayList<>();
        FindIterable<Document> docs = collection.find();
        for (Document d : docs) {
            list.add(fromDocument(d));
        }
        return list;
    }

 
    public void updateContact(Contact contact) {
  
        collection.replaceOne(eq("id", contact.getId()), toDocument(contact));
    }

  
    public void deleteById(int id) {
        collection.deleteOne(eq("id", id));
    }
}
