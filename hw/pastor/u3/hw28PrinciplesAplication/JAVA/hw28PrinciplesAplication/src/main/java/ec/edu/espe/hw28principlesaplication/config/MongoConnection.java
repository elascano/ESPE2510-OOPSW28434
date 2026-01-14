package ec.edu.espe.hw28principlesaplication.config;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class MongoConnection {
    private static MongoConnection instance;
    private MongoClient mongoClient;
    private MongoDatabase database;

    private MongoConnection() {
        try {
            String uri = "mongodb+srv://Mathews:Mathews2007@cluster0.6l9ibfh.mongodb.net/?appName=Cluster0";
            mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase("HWs"); 
            System.out.println("Successful connection .");
        } catch (Exception e) {
            System.err.println("Error connection: " + e.getMessage());
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
}
