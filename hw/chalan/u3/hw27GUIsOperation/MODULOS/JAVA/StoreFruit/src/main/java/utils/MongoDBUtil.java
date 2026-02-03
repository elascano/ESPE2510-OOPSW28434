package utils;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBUtil {

    private static final String CONNECTION_STRING =
            "mongodb+srv://kevin:kevin2001@cluster0.oxinj5p.mongodb.net/contac";

    private static final String DATABASE_NAME = "fruit_store";

    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        if (database == null) {
            MongoClient client = MongoClients.create(CONNECTION_STRING);
            database = client.getDatabase(DATABASE_NAME);
            createCollectionIfNotExists();
        }
        return database;
    }

    private static void createCollectionIfNotExists() {
        boolean exists = false;

        for (String name : database.listCollectionNames()) {
            if (name.equals("fruits")) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            database.createCollection("fruits");

            database.getCollection("fruits").insertOne(
                    new Document("name", "Apple")
                            .append("price", 1.5)
                            .append("stock", 20)
            );
            database.getCollection("fruits").insertOne(
                    new Document("name", "Banana")
                            .append("price", 0.8)
                            .append("stock", 30)
            );
            database.getCollection("fruits").insertOne(
                    new Document("name", "Orange")
                            .append("price", 1.2)
                            .append("stock", 25)
            );
            database.getCollection("fruits").insertOne(
                    new Document("name", "Strawberry")
                            .append("price", 2.0)
                            .append("stock", 15)
            );
        }
    }
}
