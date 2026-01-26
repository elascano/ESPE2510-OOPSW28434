
package ec.edu.espe.alarmsystem.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author @author Adrian Toapanta 
 */
public class Database {

    private static final String URI = "mongodb+srv://Arelys:Arelys1234@cluster0.3u6ujwz.mongodb.net/";
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URI);
            database = mongoClient.getDatabase("Singleton");
        }
        return database;
    }
}

