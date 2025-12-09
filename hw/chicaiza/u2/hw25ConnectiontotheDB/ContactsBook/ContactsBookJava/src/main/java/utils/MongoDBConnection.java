package utils;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
/**
 *
 * @author Daniel
 */
public class MongoDBConnection {

    private static MongoDatabase database;

    public static void connect() {
        try {
            String uri = "mongodb+srv://daniel:daniel2007@cluster0.v7buh9x.mongodb.net/ContactsDB?retryWrites=true&w=majority";
            MongoClient mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase("ContactsDB");
            System.out.println("Conectado a MongoDB");

        } catch (Exception e) {
            System.out.println("Error al conectar a MongoDB: " + e.getMessage());
        }
    }

    public static MongoDatabase getDatabase() {
        if (database == null) {
            connect();
        }
        return database;
    }

    public static MongoCollection<Document> getCollection(String collectionName) {
        if (database == null) {
            connect();
        }
        return database.getCollection(collectionName);
    }
}
