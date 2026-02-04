package ec.edu.espe.tools.controller;

import ec.edu.espe.tools.model.Tool;
import ec.edu.espe.tools.utils.MongoConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *@author César Vargas, Paradigm, @ESPE
 */
public class ToolController {

    private MongoCollection<Document> collection;
    private static final double IVA_RATE = 0.15;

    public ToolController() {
        MongoDatabase db = MongoConnection.getInstance().getDatabase();
        this.collection = db.getCollection("tools"); 
    }

    public double calculateIva(double price) {
        double result = price * (1 + IVA_RATE);
        return Math.round(result * 100.0) / 100.0;
    }

    public boolean createSculpture(String id, String name, double price, List<String> materials) {
        try {
            double finalPrice = calculateIva(price);
            Document doc = new Document("id", id)
                    .append("name", name)
                    .append("price", price)
                    .append("materials", materials)
                    .append("priceWithIva", finalPrice);
            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            System.err.println("Error creating: " + e.getMessage());
            return false;
        }
    }

    public List<Tool> getAllTools() {
        List<Tool> list = new ArrayList<>();
        for (Document doc : collection.find()) {
            list.add(mapDocumentToSculpture(doc));
        }
        return list;
    }

    public Tool findSculptureById(String id) {
        Document doc = collection.find(Filters.eq("id", id)).first();
        if (doc != null) {
            return mapDocumentToSculpture(doc);
        }
        return null;
    }

    public boolean updateSculpture(String id, String name, double price, List<String> materials) {
        try {
            double newPriceWithIva = calculateIva(price);

            UpdateResult result = collection.updateOne(
                    Filters.eq("id", id),
                    Updates.combine(
                            Updates.set("name", name),
                            Updates.set("price", price),
                            Updates.set("materials", materials),
                            Updates.set("priceWithIva", newPriceWithIva)
                    )
            );
            return result.getMatchedCount() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSculpture(String id) {
        try {
            DeleteResult result = collection.deleteOne(Filters.eq("id", id));
            return result.getDeletedCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    private Tool mapDocumentToSculpture(Document doc) {
        String id = doc.getString("id");
        if (id == null) {
            id = doc.getObjectId("_id").toString();
        }

        String name = doc.getString("name");
        double price = doc.getDouble("price");

        List<String> materials = doc.getList("materials", String.class);
        if (materials == null) {
            materials = new ArrayList<>();
        }

        Double storedIva = doc.getDouble("priceWithIva");
        if (storedIva == null) {
            storedIva = calculateIva(price); 
        }
        return new Tool(id, name, price, materials, storedIva);
    }
}
