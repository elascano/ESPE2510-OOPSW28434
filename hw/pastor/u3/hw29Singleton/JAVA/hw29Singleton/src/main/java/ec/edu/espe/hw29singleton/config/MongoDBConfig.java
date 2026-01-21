package ec.edu.espe.hw29singleton.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class MongoDBConfig {

    private static final MongoClient client = MongoClients.create("mongodb+srv://Mathews:Mathews2007@cluster0.6l9ibfh.mongodb.net/?appName=Cluster0");
    private static final MongoDatabase database = client.getDatabase("SingletonDB");

    private MongoDBConfig() {
    }

    public static MongoDatabase getDatabase() {
        return database;
    }
}
