package ec.edu.espe.galeryartsculpture.utils;

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

    // --- CONFIGURACIÓN BD ---
    private static final String CONNECTION_STRING = "mongodb+srv://Mathews:Mathews2007@cluster0.6l9ibfh.mongodb.net/?appName=Cluster0";
    private static final String DATABASE_NAME = "ArtGalleryDB";

    private MongoConnection() {
        try {
            mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase(DATABASE_NAME);
            System.out.println("Sucessful connection");
        } catch (Exception e) {
            System.err.println("Error in connection " + e.getMessage());
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
