package ec.edu.espe.contactstbook.controller;

/**
 *
 * @author Josue Rojas
 */
import com.mongodb.client.MongoCollection;
import ec.edu.espe.contactstbook.model.Contact;
import org.bson.Document;

public class ContactDAO {

    public boolean save(Contact Contact) {

        try {
            MongoCollection<Document> collection = MongoConnection.getConnection().getCollection("JavaContactsBook");

            Document doc = new Document("First Name", Contact.getFirstName())
                    .append("Last Name", Contact.getLastName())
                    .append("Birth Date", Contact.getBirthDate())
                    .append("Age",Contact.getAge())
                    .append("Type", Contact.getTypeOfContact())
                    .append("Sex", Contact.getSex())
                    .append("Hobbies", Contact.getHobbies())
                    .append("Comments", Contact.getComments());

            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            System.out.println("Error MongoDB:" + e.getMessage());
            return false;
        }
    }
}
