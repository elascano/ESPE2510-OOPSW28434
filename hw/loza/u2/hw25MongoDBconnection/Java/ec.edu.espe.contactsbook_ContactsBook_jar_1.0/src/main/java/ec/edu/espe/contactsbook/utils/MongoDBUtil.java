package ec.edu.espe.contactsbook.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {

    private static MongoClient mongoClient = null;

    
    private static final String CONNECTION_STRING = 
       "mongodb+srv://Steven:Steven2001@cluster0.mp8muds.mongodb.net/?appName=Cluster0";

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(CONNECTION_STRING);
        }
        
        return mongoClient.getDatabase("contactsdb");
    }    
}
