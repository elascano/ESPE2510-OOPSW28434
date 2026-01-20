package ec.edu.espe.hw29singleton.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.hw29singleton.config.MongoDBConfig;
import ec.edu.espe.hw29singleton.model.AlarmConfig;
import ec.edu.espe.hw29singleton.model.Product;
import ec.edu.espe.hw29singleton.view.ConsoleAlertView;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Joseph Medina
 */
public class StockAlertController {

    private final ConsoleAlertView view;

    public StockAlertController(ConsoleAlertView view) {
        this.view = view;
        this.view.setController(this);
    }

    public void checkStock() {
        int minimumStock = AlarmConfig.getInstance().getMinimumStock();

        MongoDatabase db = MongoDBConfig.getDatabase();
        MongoCollection<Document> collection = db.getCollection("products");

        List<Product> lowStockProducts = new ArrayList<>();

        for (Document doc : collection.find()) {
            int stock = doc.getInteger("stock");

            if (stock <= minimumStock) {
                lowStockProducts.add(
                        new Product(
                                doc.getObjectId("_id").toString(),
                                doc.getString("name"),
                                stock
                        )
                );
            }
        }

        view.showLowStockAlert(lowStockProducts);
    }

    public void onUpdateMinimumStockRequested(int newValue) {
        AlarmConfig.getInstance().updateMinimumStock(newValue);
        checkStock();
    }
}
