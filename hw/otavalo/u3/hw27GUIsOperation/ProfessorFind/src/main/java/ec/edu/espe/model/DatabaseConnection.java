package ec.edu.espe.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Arelys
 */
public class DatabaseConnection {
    private static final String CONNECTION_URI = "mongodb+srv://Arelys:Arelys1234@cluster0.3u6ujwz.mongodb.net/";
    private static final String DATABASE_NAME = "RecoverData"; 
    private static MongoClient mongoClient = null;

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            try {
                mongoClient = MongoClients.create(CONNECTION_URI);
            } catch (Exception e) {
                System.err.println("Connection error: " + e.getMessage());
            }
        }
        return mongoClient.getDatabase(DATABASE_NAME);
    }
}
