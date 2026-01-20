package ec.edu.espe.alarmservice.config;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import ec.edu.espe.alarmservice.utils.MongoConnection;
import org.bson.Document;
/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public class AlarmConfig {

    private static AlarmConfig instance;
    private int minimumStock;
    private final MongoCollection<Document> configCollection;

    private AlarmConfig() {
        this.configCollection = MongoConnection.getDatabase().getCollection("SalesConfiguration");
        Document doc = configCollection.find().first();
        this.minimumStock = (doc != null) ? doc.getInteger("minimumStock") : 10;
    }

    public static AlarmConfig getInstance() {
        if (instance == null) {
            instance = new AlarmConfig();
        }
        return instance;
    }

    public int getMinimumStock() {
        return minimumStock;
    }

    public void updateMinimumStock(int newStock) {
        this.minimumStock = newStock;
        Document firstDoc = configCollection.find().first();
        if (firstDoc != null) {
            configCollection.updateOne(firstDoc, Updates.set("minimumStock", newStock));
        } else {
            configCollection.insertOne(new Document("minimumStock", newStock));
        }
    }
}
