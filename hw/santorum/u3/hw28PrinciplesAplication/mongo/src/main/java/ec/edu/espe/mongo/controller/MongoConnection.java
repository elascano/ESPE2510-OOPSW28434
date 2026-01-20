package ec.edu.espe.mongo.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Thais Santorum
 */

public class MongoConnection {

    private static MongoClient client;
    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        if (client == null) {
            client = MongoClients.create(
                "mongodb+srv://thais:thais@cluster0.9yfzmcp.mongodb.net/"
            );
            database = client.getDatabase("ToyShopDB");
        }
        return database;
    }
}
