package ec.edu.espe.alarmsystem.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ec.edu.espe.alarmsystem.model.AlarmService;
import ec.edu.espe.alarmsystem.model.Product;
import ec.edu.espe.alarmsystem.view.FrmAlarm;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.Document;

/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */
public class AlarmController {

    private final AlarmService model;
    private final FrmAlarm view;

    public AlarmController(AlarmService model, FrmAlarm view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        this.view.setVisible(true);
        checkInventory();
    }

    public void checkInventory() {
        List<Product> products = loadProductsFromDb();
        for (Product product : products) {
            if (model.isLowStock(product)) {
                JOptionPane.showMessageDialog(view, 
                    "LOW STOCK ALERT\nProduct: " + product.getName() + 
                    "\nCurrent Stock: " + product.getStock() + 
                    "\nMinimum Required: " + model.getMinStock(),
                    "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private List<Product> loadProductsFromDb() {
        List<Product> list = new ArrayList<>();
        MongoCollection<Document> col = Database.getDatabase().getCollection("Products");
        try (MongoCursor<Document> cursor = col.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                int id = ((Number) doc.get("id")).intValue();
                String name = doc.getString("name");
                int stock = ((Number) doc.get("stock")).intValue();

                list.add(new Product(id, name, stock));
            }
        } catch (Exception e) {
            System.out.println("Error processing products: " + e.getMessage());
        }
        return list;
    }

}
