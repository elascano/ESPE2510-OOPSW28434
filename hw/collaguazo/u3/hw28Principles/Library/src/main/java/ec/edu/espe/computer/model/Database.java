package ec.edu.espe.computer.model;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


/**
 *
 * @author Pablo Collaguazo
 */
public class Database {
 
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static MongoDatabase getConnection() {
        if (mongoClient == null) {
            String uri = "mongodb+srv://Psblo:Pablo2006@cluster0.cadn1kx.mongodb.net/";
            mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase("Homework");
        }
        return database;
    }

}
