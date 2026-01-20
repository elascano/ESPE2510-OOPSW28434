package ec.edu.espe.tools.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Mikaeel Hidalgo, Paradigm, @ESPE
 */
public class MongoConnection {

    private static MongoConnection instance;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private static final String CONNECTION_STRING = "mongodb+srv://Mikael:<Mikael1897>@cluster0.fpyoe9m.mongodb.net/?appName=Cluster0";
    private static final String DATABASE_NAME = "ToolsDB";

    private MongoConnection() {
        try {
            mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase(DATABASE_NAME);
            System.out.println("Sucessful connection");
        } catch (Exception e) {
            System.err.println("Error in connection " + e.getMessage());
        }
    }

    public static MongoConnection getInstance() {
        if (instance == null) {
            instance = new MongoConnection();
        }
        return instance;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
