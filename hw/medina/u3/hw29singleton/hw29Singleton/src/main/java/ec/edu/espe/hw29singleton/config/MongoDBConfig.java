package ec.edu.espe.hw29singleton.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Joseph Medina
 */
public class MongoDBConfig {

    private static final MongoClient client = MongoClients.create("mongodb+srv://Joseph:Joseph1751774793@cluster0.h8pi0ir.mongodb.net/?appName=Cluster0");
    private static final MongoDatabase database = client.getDatabase("SingletonDB");

    private MongoDBConfig() {
    }

    public static MongoDatabase getDatabase() {
        return database;
    }
}
