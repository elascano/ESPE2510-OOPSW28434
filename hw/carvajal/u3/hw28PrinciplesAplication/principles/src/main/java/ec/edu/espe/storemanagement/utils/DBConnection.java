package ec.edu.espe.storemanagement.utils;

/**
 *
 * @author Adrian Toapanta, @ESPE
 */

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DBConnection {
    private static final String URI = "mongodb+srv://Emily:Emily2006@cluster0.ynnit6l.mongodb.net/";
    private static MongoClient mongoClient = null;

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URI);
        }
        return mongoClient.getDatabase("StoreDB");
    }
}
