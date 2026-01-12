package ec.edu.espe.actor.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

    private static MongoClient client;
    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        if (client == null) {
            client = MongoClients.create(
                "mongodb+srv://oop:oop@cluster0.9knxc.mongodb.net/?appName=Cluster0"
            );
            database = client.getDatabase("opp");
        }
        return database;
    }
}
