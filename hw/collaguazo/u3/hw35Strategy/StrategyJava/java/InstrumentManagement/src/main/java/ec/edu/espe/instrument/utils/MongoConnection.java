package ec.edu.espe.instrument.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */
public class MongoConnection {

    private static MongoConnection instance;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private static final String CONNECTION_STRING = "mongodb+srv://Arelys:Arelys1234@cluster0.3u6ujwz.mongodb.net/";
    private static final String DATABASE_NAME = "InstrumentsDB";

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
