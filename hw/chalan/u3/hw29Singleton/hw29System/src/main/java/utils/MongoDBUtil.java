package utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.hw29system.model.Photographer;
import org.bson.Document;

public class MongoDBUtil {

    private static MongoDBUtil instance;
    private MongoCollection<Document> collection;

    // --- CAMBIO AQUÍ: Usar localhost en vez de Atlas ---
    private static final String CONNECTION_STRING = "mongodb://localhost:27017/";
    // --------------------------------------------------

    private MongoDBUtil() {
        try {
            MongoClient client = MongoClients.create(CONNECTION_STRING);
            MongoDatabase database = client.getDatabase("contact"); // Aquí se crea/usa la base 'contact'
            collection = database.getCollection("photographers");
            System.out.println("Connected to Local MongoDB");
        } catch (Exception e) {
            System.err.println("Error connecting to Local Mongo: " + e.getMessage());
        }
    }

    public static MongoDBUtil getInstance() {
        if (instance == null) {
            instance = new MongoDBUtil();
        }
        return instance;
    }

    public void save(Photographer photographer) {
        Document doc = new Document("name", photographer.getName())
                .append("specialty", photographer.getSpecialty())
                .append("experience", photographer.getExperience())
                .append("hourlyRate", photographer.getHourlyRate());

        collection.insertOne(doc);
        System.out.println("Photographer saved in MongoDB");
    }

    public java.util.List<Photographer> getAll() {
        java.util.List<Photographer> photographers = new java.util.ArrayList<>();
        try {
            com.mongodb.client.FindIterable<Document> iterable = collection.find();
            for (Document doc : iterable) {
                Photographer p = new Photographer(
                    doc.getString("name"),
                    doc.getString("specialty"),
                    doc.getInteger("experience"),
                    doc.getDouble("hourlyRate")
                );
                photographers.add(p);
            }
        } catch (Exception e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }
        return photographers;
    }


public void delete(String name) {
   
    collection.deleteOne(new Document("name", name));
    System.out.println("Photographer deleted from MongoDB");
}
}