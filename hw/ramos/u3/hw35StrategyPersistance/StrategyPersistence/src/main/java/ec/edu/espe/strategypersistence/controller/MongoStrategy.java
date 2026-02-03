package ec.edu.espe.strategypersistence.controller;

import com.mongodb.client.*;
import static com.mongodb.client.model.Filters.eq;
import ec.edu.espe.strategypersistence.model.Store;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Paulo Ramos
 */

public class MongoStrategy implements PersistenceStrategy {
    
    private MongoCollection<Document> collection;

    public MongoStrategy() {
        MongoClient client = MongoClients.create("mongodb+srv://Paulo:paulo2004@cluster0.9uxqgih.mongodb.net/");
        MongoDatabase db = client.getDatabase("Store");
        this.collection = db.getCollection("Store");
    }

    @Override
    public void create(Store s) {
        Document doc = new Document("id", s.getId())
                .append("name", s.getName())
                .append("price", s.getPrice())
                .append("category", s.getCategory());
        collection.insertOne(doc);
    }

    @Override
    public Store find(int id) {
        Document doc = collection.find(eq("id", id)).first();
        return doc == null ? null : documentToStore(doc);
    }

    @Override
    public void update(int id, Store s) {
        Document doc = new Document("id", s.getId())
                .append("name", s.getName())
                .append("price", s.getPrice())
                .append("category", s.getCategory());
        collection.replaceOne(eq("id", id), doc);
    }

    @Override
    public void delete(int id) {
        collection.deleteOne(eq("id", id));
    }

    @Override
    public List<Store> loadAll() {
        List<Store> list = new ArrayList<>();
        for (Document doc : collection.find()) {
            list.add(documentToStore(doc));
        }
        return list;
    }

    private Store documentToStore(Document doc) {
        return new Store(doc.getInteger("id"), doc.getString("name"), 
                         doc.getDouble("price").floatValue(), doc.getString("category"));
    }
}