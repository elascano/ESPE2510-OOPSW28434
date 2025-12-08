package utils;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Daniel
 */
public class MongoDBConnection {
    
    private static MongoDatabase database;
    
    public static void connect() {
        try {
            String uri = "mongodb+srv://daniel:daniel2007@cluster0.v7buh9x.mongodb.net/CrealtivaStudioDB?retryWrites=true&w=majority";
            MongoClient mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase("CrealtivaStudioDB");
            System.out.println("Concetado a MongoDB");
            
        
        }catch(Exception e){
            System.out.println("Error al concetar a MongoDB"+ e.getMessage());
            
        }
    }
    public static MongoDatabase getDatabase() {
        
        return database;
        
    }
}
