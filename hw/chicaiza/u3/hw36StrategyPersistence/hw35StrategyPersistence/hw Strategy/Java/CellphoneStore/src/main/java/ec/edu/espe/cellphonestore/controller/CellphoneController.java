package ec.edu.espe.cellphonestore.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import ec.edu.espe.cellphonestore.model.Cellphone;
import java.util.ArrayList;
import java.util.List;
import utils.MongoDBConnection;

public class CellphoneController {

    private final MongoCollection<Document> collection;

    // Strategy reference
    private CellphoneUpdateStrategy updateStrategy;

    public CellphoneController() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        collection = database.getCollection("cellphones");

        // Strategy injection
        updateStrategy = new MongoUpdateStrategy();
    }

    public void create(Cellphone cellphone) {
        Document doc = new Document("id", cellphone.getId())
                .append("model", cellphone.getModel())
                .append("price", cellphone.getPrice());

        collection.insertOne(doc);
    }

    public Cellphone findById(String id) {

        Document doc = collection.find(Filters.eq("id", id)).first();

        if (doc == null) {
            return null;
        }

        return new Cellphone(
                doc.getString("id"),
                doc.getString("model"),
                doc.getDouble("price")
        );
    }

    // Strategy use
    public void update(String id, String model, double price) {
        updateStrategy.update(id, model, price);
    }

    public void delete(String id) {
        collection.deleteOne(Filters.eq("id", id));
    }

    public List<Cellphone> findAll() {
        List<Cellphone> cellphones = new ArrayList<>();

        for (Document doc : collection.find()) {
            cellphones.add(new Cellphone(
                    doc.getString("id"),
                    doc.getString("model"),
                    doc.getDouble("price")
            ));
        }
        return cellphones;
    }
}

