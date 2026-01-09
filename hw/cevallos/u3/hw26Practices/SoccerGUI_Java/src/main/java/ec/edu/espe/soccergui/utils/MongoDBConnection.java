package ec.edu.espe.soccergui.utils;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConnection {

    private static MongoDatabase database;
    private static MongoClient mongoClient;

    public static MongoDatabase getConnection() {
        if (database == null) {
            try {
                String uri = "mongodb+srv://Mateo:Mateo2006@cluster0.2mp0ve2.mongodb.net/?appName=Cluster0";

                mongoClient = MongoClients.create(uri);
                database = mongoClient.getDatabase("TestDB");

                database.runCommand(new Document("ping", 1));

            } catch (Exception e) {
                e.printStackTrace();
                database = null;
            }
        }
        return database;
    }

    public static boolean isConnected() {
        try {
            if (database == null) {
                getConnection();
            }

            database.runCommand(new Document("ping", 1));
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            database = null;
            mongoClient = null;
        }
    }
}
