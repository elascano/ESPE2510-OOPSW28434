package ec.edu.espe.contactsbook.controller;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
import ec.edu.espe.contactsbook.model.Contact;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.bson.Document;

public class ContacsBookController {

    private MongoCollection<Document> getContactsCollection() {
        MongoDatabase database = MongoDBConnection.getConnection();
        return database.getCollection("Contacts");
    }

    public boolean saveContact(Contact contact, String birthDateStr) {
        try {
            MongoCollection<Document> collection = getContactsCollection();

            Document contactDoc = new Document();
            contactDoc.append("firstName", contact.getFirtName());
            contactDoc.append("lastName", contact.getLastName());
            contactDoc.append("age", contact.getAge());
            contactDoc.append("birthDate", birthDateStr);
            contactDoc.append("typeOfContact", contact.getTypeOfContact());
            contactDoc.append("sex", contact.getSex());
            contactDoc.append("hobbies", contact.getHobbies());
            contactDoc.append("comments", contact.getComments());

            InsertOneResult result = collection.insertOne(contactDoc);

            if (result.getInsertedId() != null) {
                JOptionPane.showMessageDialog(null,
                    "Contact saved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                return true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Error saving contact: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();

        try {
            MongoCollection<Document> collection = getContactsCollection();

            try (MongoCursor<Document> cursor = collection.find().iterator()) {
                while (cursor.hasNext()) {
                    Document doc = cursor.next();

                    String idString = doc.getObjectId("_id").toHexString();
                    int id = Math.abs(idString.hashCode());

                    String firstName = doc.getString("firstName");
                    String lastName = doc.getString("lastName");
                    int age = doc.getInteger("age", 0);
                    String typeOfContact = doc.getString("typeOfContact");
                    String sex = doc.getString("sex");

                    ArrayList<String> hobbies = new ArrayList<>();
                    Object hobbiesObj = doc.get("hobbies");
                    if (hobbiesObj != null && hobbiesObj instanceof java.util.List) {
                        hobbies = (ArrayList<String>) hobbiesObj;
                    }

                    String comments = doc.getString("comments");

                    Contact contact = new Contact(id, firstName, lastName, age,
                        typeOfContact, sex, hobbies, comments);
                    contacts.add(contact);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Error getting contacts: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return contacts;
    }

    public Contact findContactById(int id) {
        try {
            MongoCollection<Document> collection = getContactsCollection();

            Document query = new Document("id", id);
            Document doc = collection.find(query).first();

            if (doc != null) {
                String firstName = doc.getString("firstName");
                String lastName = doc.getString("lastName");
                int age = doc.getInteger("age", 0);
                String typeOfContact = doc.getString("typeOfContact");
                String sex = doc.getString("sex");

                ArrayList<String> hobbies = new ArrayList<>();
                Object hobbiesObj = doc.get("hobbies");
                if (hobbiesObj != null && hobbiesObj instanceof java.util.List) {
                    hobbies = (ArrayList<String>) hobbiesObj;
                }

                String comments = doc.getString("comments");

                return new Contact(id, firstName, lastName, age,
                    typeOfContact, sex, hobbies, comments);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Error searching contact: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return null;
    }

    public boolean deleteContact(int id) {
        try {
            MongoCollection<Document> collection = getContactsCollection();

            Document query = new Document("id", id);
            long deletedCount = collection.deleteOne(query).getDeletedCount();

            if (deletedCount > 0) {
                JOptionPane.showMessageDialog(null,
                    "Contact deleted successfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null,
                    "Contact not found",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Error deleting contact: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateContact(Contact contact) {
        try {
            MongoCollection<Document> collection = getContactsCollection();

            Document query = new Document("id", contact.getId());

            Document updateDoc = new Document();
            updateDoc.append("firstName", contact.getFirtName());
            updateDoc.append("lastName", contact.getLastName());
            updateDoc.append("age", contact.getAge());
            updateDoc.append("typeOfContact", contact.getTypeOfContact());
            updateDoc.append("sex", contact.getSex());
            updateDoc.append("hobbies", contact.getHobbies());
            updateDoc.append("comments", contact.getComments());

            Document update = new Document("$set", updateDoc);
            UpdateResult result = collection.updateOne(query, update);

            if (result.getModifiedCount() > 0) {
                JOptionPane.showMessageDialog(null,
                    "Contact updated successfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null,
                    "Contact not found",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Error updating contact: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Contact> searchContactsByName(String name) {
        ArrayList<Contact> contacts = new ArrayList<>();

        try {
            MongoCollection<Document> collection = getContactsCollection();

            Document query = new Document("firstName",
                new Document("$regex", name).append("$options", "i"));

            try (MongoCursor<Document> cursor = collection.find(query).iterator()) {
                while (cursor.hasNext()) {
                    Document doc = cursor.next();

                    String idString = doc.getObjectId("_id").toHexString();
                    int id = Math.abs(idString.hashCode());

                    String firstName = doc.getString("firstName");
                    String lastName = doc.getString("lastName");
                    int age = doc.getInteger("age", 0);
                    String typeOfContact = doc.getString("typeOfContact");
                    String sex = doc.getString("sex");

                    ArrayList<String> hobbies = new ArrayList<>();
                    Object hobbiesObj = doc.get("hobbies");
                    if (hobbiesObj != null && hobbiesObj instanceof java.util.List) {
                        hobbies = (ArrayList<String>) hobbiesObj;
                    }

                    String comments = doc.getString("comments");

                    Contact contact = new Contact(id, firstName, lastName, age,
                        typeOfContact, sex, hobbies, comments);
                    contacts.add(contact);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Error searching contacts: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return contacts;
    }

    public long countContacts() {
        try {
            MongoCollection<Document> collection = getContactsCollection();
            return collection.countDocuments();
        } catch (Exception e) {
            System.err.println("Error counting contacts: " + e.getMessage());
            return 0;
        }
    }
}
