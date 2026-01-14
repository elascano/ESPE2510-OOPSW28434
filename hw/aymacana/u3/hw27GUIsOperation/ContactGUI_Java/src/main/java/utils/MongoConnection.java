/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author Mateo Cevallos
 */
public class MongoConnection {
    
    private static MongoDatabase dataBase;
    private static MongoClient mongoClient;
    
    public static MongoDatabase getConnection() {
        if (dataBase == null) {
            try {
                String uri = "mongodb+srv://Mateo:Mateo2006@cluster0.2mp0ve2.mongodb.net/?appName=Cluster0";

                mongoClient = MongoClients.create(uri);
                dataBase = mongoClient.getDatabase("TestDB");

                dataBase.runCommand(new Document("ping", 1));

            } catch (Exception e) {
                e.printStackTrace();
                dataBase = null;
            }
        }
        return dataBase;
    }

    public static boolean isConnected() {
        try {
            if (dataBase == null) {
                getConnection();
            }

            dataBase.runCommand(new Document("ping", 1));
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            dataBase = null;
            mongoClient = null;
        }
    }
    
}
