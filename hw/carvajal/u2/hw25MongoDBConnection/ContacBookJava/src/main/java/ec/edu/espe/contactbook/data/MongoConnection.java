package ec.edu.espe.contactbook.data;

/**
 *
 * @author Josue Carvajal, THE ART OF PROGRAMMING, @ESPE
 */

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

    public static final String CONNECTION_STRING = "mongodb+srv://Gabriel:Gabriel2007@cluster0.dgdm9az.mongodb.net/";
    public static final String DATABASE_NAME = "contactbookdb";
    public static final String COLLECTION_NAME = "contacts";

    private static MongoClient mongoClient = null;

    public static MongoClient getMongoClient() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(CONNECTION_STRING);
        }
        return mongoClient;
    }

    public static MongoDatabase getDatabase() {
        return getMongoClient().getDatabase(DATABASE_NAME);
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }
}
