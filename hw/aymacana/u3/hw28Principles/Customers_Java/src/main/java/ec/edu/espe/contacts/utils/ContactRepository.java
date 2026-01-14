package ec.edu.espe.contacts.utils;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
import ec.edu.espe.contacts.model.Contact;
import ec.edu.espe.contacts.model.ContactType;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

    private final MongoCollection<Document> collection;

    public ContactRepository() {
        this.collection = MongoDBConnection.getCollection();
    }

    public List<Contact> findAll() {
        List<Contact> contacts = new ArrayList<>();

        try {
            FindIterable<Document> documents = collection.find().sort(new Document("id", 1));

            for (Document doc : documents) {
                Contact contact = documentToContact(doc);
                contacts.add(contact);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error fetching contacts: " + e.getMessage(), e);
        }

        return contacts;
    }

    public Contact findById(int id) {
        try {
            Document query = new Document("id", id);
            Document doc = collection.find(query).first();

            if (doc == null) {
                throw new RuntimeException("Contact with ID " + id + " not found");
            }

            return documentToContact(doc);

        } catch (Exception e) {
            throw new RuntimeException("Error finding contact: " + e.getMessage(), e);
        }
    }

    private Contact documentToContact(Document doc) {
        // Obtener el ID (siempre es entero)
        int id = doc.getInteger("id");

        // Obtener totalSale - manejar tanto Integer como Double
        Object totalSaleObj = doc.get("totalSale");
        double totalSale;

        if (totalSaleObj instanceof Integer) {
            totalSale = ((Integer) totalSaleObj).doubleValue();
        } else if (totalSaleObj instanceof Double) {
            totalSale = (Double) totalSaleObj;
        } else {
            totalSale = 0.0;
        }

        // Obtener discount - manejar tanto Integer como Double
        Object discountObj = doc.get("discount");
        int discount;

        if (discountObj instanceof Integer) {
            discount = (Integer) discountObj;
        } else if (discountObj instanceof Double) {
            discount = ((Double) discountObj).intValue();
        } else {
            discount = 0;
        }

        return new Contact(
                id,
                doc.getString("fullName"),
                doc.getString("email"),
                ContactType.fromString(doc.getString("type")),
                discount,
                totalSale
        );
    }
}
