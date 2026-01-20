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

    private static final String CONNECTION_STRING =
            "mongodb+srv://kevin:kevin2001@cluster0.oxinj5p.mongodb.net/contact";

    private MongoDBUtil() {
        MongoClient client = MongoClients.create(CONNECTION_STRING);
        MongoDatabase database = client.getDatabase("contact");
        collection = database.getCollection("photographers");

        System.out.println("Connected to MongoDB Atlas");
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
}
