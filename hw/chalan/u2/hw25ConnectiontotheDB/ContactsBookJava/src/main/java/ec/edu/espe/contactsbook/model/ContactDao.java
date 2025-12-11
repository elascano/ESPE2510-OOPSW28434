package ec.edu.espe.contactsbook.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import utils.MongoDBConnection;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;

public class ContactDao {

    private static int lastId = 0;
    private final MongoCollection<Document> collection;

    public ContactDao() {
        this.collection = MongoDBConnection.getCollection("contacts");
        initializeLastId();
    }
    
  
    private int getIntFromDocument(Document doc, String key) {
        Object value = doc.get(key);
        if (value == null) {
            return 0; // Si el campo no existe (documento de Python), devuelve 0.
        }
        
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof String) {
            try {
            
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
            
                return 0; 
            }
        } else {
            
            return 0;
        }
    }
    

    private void initializeLastId(){
        
        MongoCursor<Document> cursor = collection.find().iterator();
        
        int maxId=0;
        
        while (cursor.hasNext()){
            
            Document doc = cursor.next();
            
            int currentId = getIntFromDocument(doc, "id");
            
            if (currentId > maxId){
                maxId = currentId;
            }
        }
        lastId = maxId;
        cursor.close();
    }   
    
    public void delete(int id) {
        collection.deleteOne(Filters.eq("id", id));
        System.out.println("Contact deleted from MongoDB! ID: " + id);
    }

    private int generateId() {
        
        lastId++;
        return lastId;
    }

    public void insert(Contact contact) {

        int newId = generateId();
        contact.setId(newId);

        Document doc = new Document()
                .append("id", contact.getId())
                .append("firstName", contact.getFirstName())
                .append("lastName", contact.getLastName())
                .append("birthDate", contact.getBirthDate())
                .append("age", contact.getAge())
                .append("typeOfContact", contact.getTypeOfContact())
                .append("sex", contact.getSex())
                .append("hobbies", contact.getHobbies())
                .append("comments", contact.getComments());

        collection.insertOne(doc);
        System.out.println("Contact uploaded to MongoDB! ID: " + newId);
    }
    
    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                
                int contactId = getIntFromDocument(doc, "id");
                int age = getIntFromDocument(doc, "age");
                
                String firstName = doc.getString("firstName");
                String lastName = doc.getString("lastName");
                
                if (firstName == null && doc.containsKey("name")) {
                    String fullName = doc.getString("name");
                    if (fullName != null) {
                        String[] parts = fullName.trim().split("\\s+", 2);
                        firstName = parts.length > 0 ? parts[0] : "";
                        lastName = parts.length > 1 ? parts[1] : "";
                    }
                }
                
                
                if (firstName == null) firstName = "N/A";
                if (lastName == null) lastName = "";

                
                Contact contact = new Contact(
                    contactId,
                    firstName,
                    lastName,
                    doc.getDate("birthDate"),
                    age,
                    doc.getString("typeOfContact"),
                    doc.getString("sex"),
                    (ArrayList<String>) doc.get("hobbies"),
                    doc.getString("comments")
                );
                contacts.add(contact);
            }
        } finally {
            cursor.close();
        }
        return contacts;
    }
    
    public Contact getContactById(int id) {
    Bson filter = Filters.eq("id", id);
    Document doc = collection.find(filter).first();

    if (doc != null) {
      
        int contactId = getIntFromDocument(doc, "id");
        int age = getIntFromDocument(doc, "age");
        
        String firstName = doc.getString("firstName");
        String lastName = doc.getString("lastName");
        
        return new Contact(
            contactId,
            firstName,
            lastName,
            doc.getDate("birthDate"),
            age,
            doc.getString("typeOfContact"),
            doc.getString("sex"),
            (ArrayList<String>) doc.get("hobbies"), 
            doc.getString("comments")
        );
    }
    return null; 
}
}