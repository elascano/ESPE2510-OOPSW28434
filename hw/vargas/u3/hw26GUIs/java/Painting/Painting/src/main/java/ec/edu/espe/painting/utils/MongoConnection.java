package ec.edu.espe.painting.utils;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;    
/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
public class MongoConnection {

    private static MongoConnection instance;
    private MongoClient mongoClient;
    private MongoDatabase database;

    private static final String CONNECTION_STRING = "mongodb+srv://Cesar:Cesar2006@cluster0.tgbv2qc.mongodb.net/";
    private static final String DATABASE_NAME = "GalleryDB";

    private MongoConnection() {
        try {
            mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase(DATABASE_NAME);
            System.out.println("Conexión a MongoDB exitosa.");
        } catch (Exception e) {
            System.err.println("Error conectando a MongoDB: " + e.getMessage());
        }
    }
    public static MongoConnection getInstance() {
        if (instance == null) {
            instance = new MongoConnection();
        }
        return instance;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexión a MongoDB cerrada.");
        }
    }
}
