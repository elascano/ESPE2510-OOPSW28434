package ec.edu.espe.painting.controller;

import ec.edu.espe.painting.model.Painting;
import ec.edu.espe.painting.utils.MongoConnection;
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
 * @author César Vargas, Paradigm, @ESPE
 */
public class PaintingController {

    private MongoCollection<Document> collection;
    private static final double IVA_RATE = 0.15;

    public PaintingController() {
        MongoDatabase db = MongoConnection.getInstance().getDatabase();
        this.collection = db.getCollection("paintings");
    }

    public double calculateIva(double price) {
        double result = price * (1 + IVA_RATE);
        return Math.round(result * 100.0) / 100.0;
    }

    public boolean createPainting(String id, String name, double price, List<String> colors) {
        try {
            double priceWithIva = calculateIva(price);

            Document doc = new Document("id", id)
                    .append("name", name)
                    .append("price", price)
                    .append("colors", colors)
                    .append("priceWithIva", priceWithIva);

            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            System.err.println("Error creating painting: " + e.getMessage());
            return false;
        }
    }

    public List<Painting> getAllPaintings() {
        List<Painting> list = new ArrayList<>();
        for (Document doc : collection.find()) {
            try {
                list.add(mapDocumentToPainting(doc));
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return list;
    }

    public Painting findPaintingById(String id) {
        Document doc = collection.find(Filters.eq("id", id)).first();
        if (doc != null) {
            return mapDocumentToPainting(doc);
        }
        return null;
    }
    public boolean updatePainting(String id, String name, double price, List<String> colors) {
        try {
            double priceWithIva = calculateIva(price);

            UpdateResult result = collection.updateOne(
                    Filters.eq("id", id), 
                    Updates.combine(
                            Updates.set("name", name),
                            Updates.set("price", price),
                            Updates.set("colors", colors),
                            Updates.set("priceWithIva", priceWithIva)
                    )
            );
            return result.getMatchedCount() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean deletePainting(String id) {
        try {
            DeleteResult result = collection.deleteOne(Filters.eq("id", id));
            return result.getDeletedCount() > 0;

        } catch (Exception e) {
            System.err.println("Error deleting: " + e.getMessage());
            return false;
        }
    }

    private Painting mapDocumentToPainting(Document doc) {
        Object idObj = doc.get("id");
        String id;

        if (idObj != null) {
            id = idObj.toString(); 
        } else {
            id = doc.getObjectId("_id").toString();
        }

        String name = doc.getString("name");
        Number priceNum = (Number) doc.get("price");
        double price = (priceNum != null) ? priceNum.doubleValue() : 0.0;

        List<String> colors = doc.getList("colors", String.class);

        Number ivaNum = (Number) doc.get("priceWithIva");
        Double storedIva = (ivaNum != null) ? ivaNum.doubleValue() : null;

        if (storedIva == null) {
            storedIva = calculateIva(price);
        }

        return new Painting(id, name, price, colors, storedIva);
    }
}
