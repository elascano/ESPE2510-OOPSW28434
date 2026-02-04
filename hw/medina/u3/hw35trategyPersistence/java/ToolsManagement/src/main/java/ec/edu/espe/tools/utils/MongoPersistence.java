package ec.edu.espe.tools.utils;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import ec.edu.espe.tools.model.Tool;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
public class MongoPersistence implements Persistence {

    private MongoCollection<Document> collection;

    public MongoPersistence() {
        MongoDatabase db = MongoConnection.getInstance().getDatabase();
        this.collection = db.getCollection("tools");
    }

    @Override
    public boolean create(Tool tool) {
        try {
            Document doc = new Document("id", tool.getId())
                    .append("name", tool.getName())
                    .append("price", tool.getPrice())
                    .append("materials", tool.getMaterials())
                    .append("priceWithIva", tool.getPriceWithIva());
            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            System.err.println("Error creating in Mongo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Tool> read() {
        List<Tool> list = new ArrayList<>();
        for (Document doc : collection.find()) {
            list.add(mapDocumentToTool(doc));
        }
        return list;
    }

    @Override
    public boolean update(String id, Tool tool) {
        try {
            UpdateResult result = collection.updateOne(
                    Filters.eq("id", id),
                    Updates.combine(
                            Updates.set("name", tool.getName()),
                            Updates.set("price", tool.getPrice()),
                            Updates.set("materials", tool.getMaterials()),
                            Updates.set("priceWithIva", tool.getPriceWithIva())
                    )
            );
            return result.getMatchedCount() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            DeleteResult result = collection.deleteOne(Filters.eq("id", id));
            return result.getDeletedCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Tool find(String id) {
        Document doc = collection.find(Filters.eq("id", id)).first();
        if (doc != null) {
            return mapDocumentToTool(doc);
        }
        return null;
    }

    private Tool mapDocumentToTool(Document doc) {
        String id = doc.getString("id");
        String name = doc.getString("name");
        double price = doc.getDouble("price");
        List<String> materials = doc.getList("materials", String.class);
        Double priceWithIva = doc.getDouble("priceWithIva");
        return new Tool(id, name, price, materials, priceWithIva);
    }
}
