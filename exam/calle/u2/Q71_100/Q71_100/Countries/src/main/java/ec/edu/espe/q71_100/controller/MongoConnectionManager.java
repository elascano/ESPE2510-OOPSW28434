package ec.edu.espe.q71_100.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnectionManager {

    private static final String CONNECTION_STRING = 
            "mongodb+srv://Emily:Emily2006@cluster0.ynnit6l.mongodb.net/";
    private static final String DATABASE_NAME = "Q71_100"; 
    
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        if (database == null) {
            try {
                mongoClient = MongoClients.create(CONNECTION_STRING);
                
                database = mongoClient.getDatabase(DATABASE_NAME);
                System.out.println("Connection to MongoDB Atlas established.");
                
            } catch (Exception e) {
                System.err.println("Error connecting to MongoDB Atlas: " + e.getMessage());
            }
        }
        return database;
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
            database = null;
            System.out.println("Connection to MongoDB Atlas closed.");
        }
    }
}