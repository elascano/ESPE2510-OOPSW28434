package ec.edu.espe.alarmsystem.model;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import ec.edu.espe.alarmsystem.controller.Database;
import org.bson.Document;

/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */
public class AlarmService {

    private static AlarmService instance;
    private int minStock;

    private AlarmService() {
       this.minStock = loadMinStockFromDb();
    }

    public static AlarmService getInstance() {
        if (instance == null) {
            instance = new AlarmService();
        }
        return instance;
    }

    private int loadMinStockFromDb() {
        MongoCollection<Document> config = Database.getDatabase().getCollection("Config");
        Document document = config.find(eq("type", "alarm_config")).first();
        if (document == null) {
            config.insertOne(new Document("type", "alarm_config").append("minStock", 10));
            return 10;
        }
        return document.getInteger("minStock");
    }

    public void updateMinStock(int newStock) {
        this.minStock = newStock;
        Database.getDatabase().getCollection("Config").updateOne(eq("type", "alarm_config"), set("minStock", newStock));
            
    }

    public boolean isLowStock(Product product) {
        return product != null && product.getStock() < minStock;
    }

    public int getMinStock() {
        return minStock;
    }
}

