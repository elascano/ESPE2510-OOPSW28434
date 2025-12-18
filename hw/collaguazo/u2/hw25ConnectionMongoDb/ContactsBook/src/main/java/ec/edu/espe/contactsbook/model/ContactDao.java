package ec.edu.espe.contactsbook.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import utils.MongoDBConnection;

public class ContactDao {

    private static int lastId = 0;
    private final MongoCollection<Document> collection;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ContactDao() {
        this.collection = MongoDBConnection.getCollection("ContactsJava");
        initializeLastId();
    }

    private void initializeLastId() {
        MongoCursor<Document> cursor = collection.find().iterator();
        int maxId = 0;

        while (cursor.hasNext()) {
            Document doc = cursor.next();
            if (doc.containsKey("id")) {
                int currentId = doc.getInteger("id");
                if (currentId > maxId) {
                    maxId = currentId;
                }
            }
        }
        lastId = maxId;
    }

    private int generateId() {
        lastId++;
        return lastId;
    }

    public void insert(Contact contact) {
        int newId = generateId();
        contact.setId(newId);

        // Guardamos la fecha como String
        String birthDateStr = sdf.format(contact.getBirthDate());

        Document doc = new Document()
                .append("id", contact.getId())
                .append("firstName", contact.getFirstName())
                .append("lastName", contact.getLastName())
                .append("birthDate", birthDateStr)
                .append("age", contact.getAge())
                .append("typeOfContact", contact.getTypeOfContact())
                .append("sex", contact.getSex())
                .append("hobbies", contact.getHobbies())
                .append("comments", contact.getComments());

        collection.insertOne(doc);
        System.out.println("Contact uploaded to MongoDB! ID: " + newId);
    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();

            // Leemos hobbies de manera segura
            List<String> hobbies = (List<String>) doc.get("hobbies");
            ArrayList<String> hobbiesList = hobbies != null ? new ArrayList<>(hobbies) : new ArrayList<>();

            // Parseamos la fecha desde String
            String birthDateStr = doc.getString("birthDate");
            Date birthDate = null;
            try {
                if (birthDateStr != null) {
                    birthDate = sdf.parse(birthDateStr);
                }
            } catch (ParseException e) {
            }

            Contact contact = new Contact(
                    doc.getInteger("id"),
                    doc.getString("firstName"),
                    
                    doc.getString("lastName"),
                    birthDate,
                    doc.getInteger("age"),
                    doc.getString("typeOfContact"),
                    doc.getString("sex"),
                    hobbiesList,
                    doc.getString("comments")
            );
            contacts.add(contact);
        }

        return contacts;
    }
}