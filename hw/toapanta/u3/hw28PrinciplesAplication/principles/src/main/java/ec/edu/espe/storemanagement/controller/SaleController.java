package ec.edu.espe.storemanagement.controller;
/**
 *
 * @author Adrian Toapanta, @ESPE
 */
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.storemanagement.utils.DBConnection;
import ec.edu.espe.storemanagement.model.Sale;
import org.bson.Document;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.eq;
import javax.swing.table.DefaultTableModel;

public class SaleController {
    private final MongoCollection<Document> collection;

    public SaleController() {
        MongoDatabase db = DBConnection.getDatabase();
        this.collection = db.getCollection("Sales");
    }

    public void create(Sale sale) {
        Document doc = new Document("productName", sale.getProductName())
                .append("unitPrice", sale.getUnitPrice())
                .append("quantity", sale.getQuantity())
                .append("totalPrice", sale.getTotalPrice());
        collection.insertOne(doc);
    }

    public DefaultTableModel getTableModel() {
        String[] columns = {"Product", "Unit Price", "Quantity", "Total to Pay"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Document doc : collection.find()) {
            Object[] row = {
                doc.get("productName"),
                doc.get("unitPrice"),
                doc.get("quantity"),
                doc.get("totalPrice")
            };
            model.addRow(row);
        }
        return model;
    }

    public void update(String originalName, Sale sale) {
        Document updatedDoc = new Document("productName", sale.getProductName())
                .append("unitPrice", sale.getUnitPrice())
                .append("quantity", sale.getQuantity())
                .append("totalPrice", sale.getTotalPrice());
        collection.updateOne(eq("productName", originalName), new Document("$set", updatedDoc));
    }

    public void delete(String name) {
        collection.deleteOne(eq("productName", name));
    }


    public Document findProductByName(String name) {
        return collection.find(eq("productName", name)).first();
    }
}