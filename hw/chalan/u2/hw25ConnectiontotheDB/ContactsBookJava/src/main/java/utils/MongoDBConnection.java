package utils;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
/**
 *
 * @author kevin chalan, @ESPE
 */
public class MongoDBConnection {

    private static MongoDatabase database;

    public static void connect() {
        try {
           
            String uri = "mongodb+srv://kevin:kevin2001@cluster0.oxinj5p.mongodb.net/contac";
            
            MongoClient mongoClient = MongoClients.create(uri);
            
            
            database = mongoClient.getDatabase("contac"); 
            
            System.out.println("Conectado a MongoDB (Base de datos: contac)");

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