package utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
public class MongoDBConnection {
    
    private static final String URI = "mongodb+srv://kevin:kevin2001@cluster0.oxinj5p.mongodb.net/";
    private static MongoClient mongoClient = null;

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            try {
                mongoClient = MongoClients.create(URI);
            } catch (Exception e) {
                System.err.println("Error connecting to MongoDB Atlas: " + e.getMessage());
            }
        }
        return mongoClient.getDatabase("StrategyChalan");
    }
}