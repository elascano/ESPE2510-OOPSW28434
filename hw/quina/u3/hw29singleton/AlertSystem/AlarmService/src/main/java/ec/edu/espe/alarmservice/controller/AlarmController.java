package ec.edu.espe.alarmservice.controller;

import com.mongodb.client.MongoCollection;
import ec.edu.espe.alarmservice.config.AlarmConfig;
import ec.edu.espe.alarmservice.utils.MongoConnection;
import ec.edu.espe.alarmservice.view.FrmAlarm;
import org.bson.Document;

/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public class AlarmController {

    private final AlarmConfig config = AlarmConfig.getInstance();
    private final MongoCollection<Document> productCollection;

    public AlarmController() {
        this.productCollection = MongoConnection.getDatabase().getCollection("Products");
    }

    public void handleUpdate(String input, FrmAlarm view) {
        try {
            int newLimit = Integer.parseInt(input);
            config.updateMinimumStock(newLimit);

            view.displayMessage("Configuration updated in InventoryDB");
            checkStockLevels(view);
        } catch (NumberFormatException e) {
            view.showError("Please enter a valid number");
        }
    }

    public void checkStockLevels(FrmAlarm view) {
        for (Document doc : productCollection.find()) {
            int stock = doc.getInteger("stock");
            if (stock <= config.getMinimumStock()) {
                view.showLowStockAlert(
                        doc.getInteger("id"),
                        doc.getString("name"),
                        stock
                );
            }
        }
    }
}
