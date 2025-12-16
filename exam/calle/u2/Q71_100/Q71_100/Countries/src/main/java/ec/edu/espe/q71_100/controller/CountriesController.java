package ec.edu.espe.q71_100.controller;

/**
 *
 * @author Emily Calle, @ESPE
 */
import ec.edu.espe.q71_100.model.Countries;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
public class CountriesController {
    private final MongoCollection<Document> contactCollection;
    
    private static final String COLLECTION_NAME = "Countries"; 
     public CountriesController() {
        MongoDatabase db = MongoConnectionManager.getDatabase();
        if (db != null) {
            this.contactCollection = db.getCollection(COLLECTION_NAME);
        } else {
            this.contactCollection = null;
        }
    }
     
     public boolean create(Countries country) {
        if (contactCollection == null) {
            System.err.println("Collection not available. Cannot save countrie.");
            return false;
        }
         try {
            Document document = new Document()
                    .append("Name", country.getName())
                    .append("Capital", country.getCapital())
                    .append("Ubication", country.getUbication())
                    .append("numberOfProvinces", country.getNumberOfProvinces());
                    

            contactCollection.insertOne(document);
            System.out.println("Countrie" + country.getName() + " successfully saved to MongoDB.");
            return true;
        } catch (Exception e) {
            System.err.println("Error inserting contact into MongoDB: " + e.getMessage());
            return false;
        }
    }
    

}