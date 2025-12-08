package ec.edu.espe.contactsbook.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    private static MongoClient client;
    private static MongoDatabase database;

    public static MongoDatabase connect() {
        if (database == null) {
            try {
                String uri = "mongodb+srv://kevin:kevin2001@cluster0.oxinj5p.mongodb.net/";
                client = MongoClients.create(uri);
                database = client.getDatabase("ContactsDB");

                System.out.println("✅ Conectado a MongoDB!");
            } catch (Exception e) {
                System.out.println("❌ Error al conectar a MongoDB:");
                e.printStackTrace();
            }
        }
        return database;
    }
}
