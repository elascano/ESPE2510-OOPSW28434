package utils;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Daniel
 */
public class MongoDBConnection {
        
    private static final String URI = "mongodb+srv://Gabriel:Gabriel2007@cluster0.dgdm9az.mongodb.net/";
    
    private static MongoDatabase database;
    
    public static MongoDatabase getDataBase(){
        
        if (database == null){
            
            MongoClient client = MongoClients.create(URI);
            database = client.getDatabase("school");
        }
        
        return database;
        
    }
}
