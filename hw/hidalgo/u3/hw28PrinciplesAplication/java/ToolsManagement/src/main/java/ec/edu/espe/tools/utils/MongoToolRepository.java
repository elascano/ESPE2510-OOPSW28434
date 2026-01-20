package ec.edu.espe.tools.utils;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import ec.edu.espe.tools.model.Tool;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mikael Hidalgo, @ESPE
 */
public class MongoToolRepository implements IToolRepository {
    private final MongoCollection<Document> collection;

    public MongoToolRepository() {
        this.collection = MongoConnection.getInstance().getDatabase().getCollection("tools");
    }

    @Override
    public boolean create(Tool tool) {
        if (findById(tool.getId()) != null) return false;
        try {
            collection.insertOne(toDocument(tool));
            return true;
        } catch (Exception e) { return false; }
    }

    @Override
    public boolean update(Tool tool) {
        try {
            return collection.updateOne(Filters.eq("id", tool.getId()), 
                Updates.combine(
                    Updates.set("name", tool.getName()),
                    Updates.set("price", tool.getPrice()),
                    Updates.set("materials", tool.getMaterials()),
                    Updates.set("priceWithIva", tool.getPriceWithIva())
                )).getMatchedCount() > 0;
        } catch (Exception e) { return false; }
    }

    @Override
    public boolean delete(String id) {
        return collection.deleteOne(Filters.eq("id", id)).getDeletedCount() > 0;
    }

    @Override
    public Tool findById(String id) {
        Document doc = collection.find(Filters.eq("id", id)).first();
        return toModel(doc);
    }

    @Override
    public List<Tool> findAll() {
        List<Tool> list = new ArrayList<>();
        for (Document doc : collection.find()) list.add(toModel(doc));
        return list;
    }

    private Document toDocument(Tool t) {
        return new Document("id", t.getId())
                .append("name", t.getName())
                .append("price", t.getPrice())
                .append("materials", t.getMaterials())
                .append("priceWithIva", t.getPriceWithIva());
    }

    private Tool toModel(Document d) {
        if (d == null) return null;
        return new Tool(
            d.getString("id"), d.getString("name"),
            d.getDouble("price"), d.getList("materials", String.class),
            d.getDouble("priceWithIva")
        );
    }
}
