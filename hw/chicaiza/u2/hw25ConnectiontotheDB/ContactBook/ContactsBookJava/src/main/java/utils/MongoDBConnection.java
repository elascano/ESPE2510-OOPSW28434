package utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static void connect() {
        try {
            // 👉 TU URI (coloca el tuyo)
            String uri = "mongodb+srv://daniel:daniel2007@cluster0.v7buh9x.mongodb.net/";

            mongoClient = MongoClients.create(uri);

            // 👉 Nombre de la base (NO pongas la URL)
            database = mongoClient.getDatabase("ContactsBookDB");

            System.out.println("Conectado correctamente a MongoDB Atlas");

        } catch (Exception e) {
            System.out.println("Error al conectar a MongoDB: " + e.getMessage());
        }
    }

    public static MongoDatabase getDatabase() {
        return database;
    }
}
