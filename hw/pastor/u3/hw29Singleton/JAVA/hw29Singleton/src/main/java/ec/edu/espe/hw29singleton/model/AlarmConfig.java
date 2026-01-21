package ec.edu.espe.hw29singleton.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import ec.edu.espe.hw29singleton.config.MongoDBConfig;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class AlarmConfig {

    private static AlarmConfig instance;
    private int minimumStock;

    private AlarmConfig(int minimumStock) {
        this.minimumStock = minimumStock;
    }

    // Singleton
    public static synchronized AlarmConfig getInstance() {
        if (instance == null) {
            instance = new AlarmConfig(loadFromDatabase());
        }
        return instance;
    }

    public int getMinimumStock() {
        return minimumStock;
    }

    public void updateMinimumStock(int newMinimumStock) {
        this.minimumStock = newMinimumStock;
        saveToDatabase(newMinimumStock);
    }

    private static int loadFromDatabase() {
        MongoDatabase db = MongoDBConfig.getDatabase();
        MongoCollection<Document> collection = db.getCollection("sales_configuration");

        Document doc = collection.find(eq("_id", "alarm_config")).first();

        if (doc == null) {
            Document defaultConfig = new Document("_id", "alarm_config")
                    .append("minimumStock", 10);
            collection.insertOne(defaultConfig);
            return 10;
        }

        return doc.getInteger("minimumStock");
    }

    private static void saveToDatabase(int newMinimumStock) {
        MongoDatabase db = MongoDBConfig.getDatabase();
        MongoCollection<Document> collection = db.getCollection("sales_configuration");

        collection.updateOne(
                eq("_id", "alarm_config"),
                new Document("$set", new Document("minimumStock", newMinimumStock))
        );
    }
}
