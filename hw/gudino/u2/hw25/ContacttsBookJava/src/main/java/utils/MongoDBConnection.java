package utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConnection {

    private static MongoDatabase database;

    public static void connect() {
        try {
            String uri = "mongodb+srv://Bryan:Bryan2000@cluster0.sx9cpnq.mongodb.net/ContactsDB?retryWrites=true&w=majority";

            MongoClient mongoClient = MongoClients.create(uri);

            database = mongoClient.getDatabase("ContactsDB");

            System.out.println("Conectado a MongoDB - Base: ContactsDB");

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
