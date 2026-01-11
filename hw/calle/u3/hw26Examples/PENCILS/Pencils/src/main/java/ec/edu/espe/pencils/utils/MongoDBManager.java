package ec.edu.espe.pencils.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBManager {
    private static final String URI = "mongodb+srv://Emily:Emily2006@cluster0.ynnit6l.mongodb.net/";
    private static final String DATABASE_NAME = "PencilsDB";

    public static MongoDatabase getDatabase() {
        MongoClient mongoClient = MongoClients.create(URI);
        return mongoClient.getDatabase(DATABASE_NAME);
    }
}