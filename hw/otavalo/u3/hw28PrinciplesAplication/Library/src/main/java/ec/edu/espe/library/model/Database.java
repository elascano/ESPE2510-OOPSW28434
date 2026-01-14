package ec.edu.espe.library.model;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */
public class Database {
 
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static MongoDatabase getConnection() {
        if (mongoClient == null) {
            String uri = "mongodb+srv://Arelys:Arelys1234@cluster0.3u6ujwz.mongodb.net/";
            mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase("Homework");
        }
        return database;
    }

}
