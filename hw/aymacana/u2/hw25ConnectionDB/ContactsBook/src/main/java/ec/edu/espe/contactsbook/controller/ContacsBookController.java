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
<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
=======
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
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

<<<<<<< HEAD
            int nextId = getNextContactId();

            int age = calculateAge(birthDateStr);

=======
<<<<<<< HEAD
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
            Document contactDoc = new Document();
            contactDoc.append("id", nextId);
            contactDoc.append("firstName", contact.getFirtName());
            contactDoc.append("lastName", contact.getLastName());
<<<<<<< HEAD
            contactDoc.append("age", age);
=======
            contactDoc.append("age", contact.getAge());
=======
            int nextId = getNextContactId();

            int age = calculateAge(birthDateStr);

            Document contactDoc = new Document();
            contactDoc.append("id", nextId);
            contactDoc.append("firstName", contact.getFirtName());
            contactDoc.append("lastName", contact.getLastName());
            contactDoc.append("age", age);
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
            contactDoc.append("birthDate", birthDateStr);
            contactDoc.append("typeOfContact", contact.getTypeOfContact());
            contactDoc.append("sex", contact.getSex());
            contactDoc.append("hobbies", contact.getHobbies());
            contactDoc.append("comments", contact.getComments());

            InsertOneResult result = collection.insertOne(contactDoc);

            if (result.getInsertedId() != null) {
                JOptionPane.showMessageDialog(null,
<<<<<<< HEAD
                        "Contact saved successfully!\nID: " + nextId,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
=======
<<<<<<< HEAD
                    "Contact saved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
=======
                        "Contact saved successfully!\nID: " + nextId,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
                return true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
<<<<<<< HEAD
                    "Error saving contact: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
=======
<<<<<<< HEAD
                "Error saving contact: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
=======
                    "Error saving contact: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
            e.printStackTrace();
        }
        return false;
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
    public int calculateAge(String birthDateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = sdf.parse(birthDateStr);
            Calendar birthCal = Calendar.getInstance();
            birthCal.setTime(birthDate);

            Calendar today = Calendar.getInstance();

            int age = today.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);

            if (today.get(Calendar.MONTH) < birthCal.get(Calendar.MONTH)) {
                age--;
            } else if (today.get(Calendar.MONTH) == birthCal.get(Calendar.MONTH)
                    && today.get(Calendar.DAY_OF_MONTH) < birthCal.get(Calendar.DAY_OF_MONTH)) {
                age--;
            }

            return age;
        } catch (Exception e) {
            System.err.println("Error calculating age: " + e.getMessage());
            return 0;
        }
    }

    public int getNextContactId() {
        try {
            MongoCollection<Document> collection = getContactsCollection();

            Document sort = new Document("id", -1);
            Document highestIdDoc = collection.find()
                    .sort(sort)
                    .limit(1)
                    .first();

            if (highestIdDoc != null && highestIdDoc.containsKey("id")) {
                int lastId = highestIdDoc.getInteger("id");
                return lastId + 1;
            } else {
                return 1;
            }

        } catch (Exception e) {
            System.err.println("Error getting next ID: " + e.getMessage());
            return 1;
        }
    }

<<<<<<< HEAD
=======
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();

        try {
            MongoCollection<Document> collection = getContactsCollection();

            try (MongoCursor<Document> cursor = collection.find().iterator()) {
                while (cursor.hasNext()) {
                    Document doc = cursor.next();

<<<<<<< HEAD
                    int id = doc.getInteger("id", 0);
=======
<<<<<<< HEAD
                    String idString = doc.getObjectId("_id").toHexString();
                    int id = Math.abs(idString.hashCode());
=======
                    int id = doc.getInteger("id", 0);
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e

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
<<<<<<< HEAD
                            typeOfContact, sex, hobbies, comments);
=======
<<<<<<< HEAD
                        typeOfContact, sex, hobbies, comments);
=======
                            typeOfContact, sex, hobbies, comments);
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
                    contacts.add(contact);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
<<<<<<< HEAD
                    "Error getting contacts: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
=======
<<<<<<< HEAD
                "Error getting contacts: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
=======
                    "Error getting contacts: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
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
<<<<<<< HEAD
                        typeOfContact, sex, hobbies, comments);
=======
<<<<<<< HEAD
                    typeOfContact, sex, hobbies, comments);
=======
                        typeOfContact, sex, hobbies, comments);
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
<<<<<<< HEAD
                    "Error searching contact: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
=======
<<<<<<< HEAD
                "Error searching contact: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
=======
                    "Error searching contact: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
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
<<<<<<< HEAD
                        "Contact deleted successfully",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Contact not found",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
=======
<<<<<<< HEAD
                    "Contact deleted successfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null,
                    "Contact not found",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
=======
                        "Contact deleted successfully",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Contact not found",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
<<<<<<< HEAD
                    "Error deleting contact: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
=======
<<<<<<< HEAD
                "Error deleting contact: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
=======
                    "Error deleting contact: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
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
<<<<<<< HEAD
                        "Contact updated successfully",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Contact not found",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
=======
<<<<<<< HEAD
                    "Contact updated successfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null,
                    "Contact not found",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
=======
                        "Contact updated successfully",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Contact not found",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
<<<<<<< HEAD
                    "Error updating contact: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
=======
<<<<<<< HEAD
                "Error updating contact: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
=======
                    "Error updating contact: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Contact> searchContactsByName(String name) {
        ArrayList<Contact> contacts = new ArrayList<>();

        try {
            MongoCollection<Document> collection = getContactsCollection();

            Document query = new Document("firstName",
<<<<<<< HEAD
                    new Document("$regex", name).append("$options", "i"));
=======
<<<<<<< HEAD
                new Document("$regex", name).append("$options", "i"));
=======
                    new Document("$regex", name).append("$options", "i"));
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e

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
<<<<<<< HEAD
                            typeOfContact, sex, hobbies, comments);
=======
<<<<<<< HEAD
                        typeOfContact, sex, hobbies, comments);
=======
                            typeOfContact, sex, hobbies, comments);
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
                    contacts.add(contact);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
<<<<<<< HEAD
                    "Error searching contacts: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
=======
<<<<<<< HEAD
                "Error searching contacts: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
=======
                    "Error searching contacts: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
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
