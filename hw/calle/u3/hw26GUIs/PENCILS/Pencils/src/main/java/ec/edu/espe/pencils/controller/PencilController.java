package ec.edu.espe.pencils.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import ec.edu.espe.pencils.model.Pencil;
import ec.edu.espe.pencils.utils.MongoDBManager;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

public class PencilController {
    private final MongoCollection<Document> collection;

    public PencilController() {
        this.collection = MongoDBManager.getDatabase().getCollection("pencils");
    }

    public void create(Pencil pencil) {
        Document doc = new Document("id", pencil.getId())
                .append("brand", pencil.getBrand())
                .append("color", pencil.getColor())
                .append("price", pencil.getPrice());
        collection.insertOne(doc);
    }

    public List<Pencil> readAll() {
        List<Pencil> pencils = new ArrayList<>();
        for (Document doc : collection.find()) {
            pencils.add(new Pencil(
                doc.getString("id"),
                doc.getString("brand"),
                doc.getString("color"),
                doc.getDouble("price")
            ));
        }
        return pencils;
    }

    public void update(Pencil pencil) {
        Bson filter = Filters.eq("id", pencil.getId());
        Bson updates = Updates.combine(
                Updates.set("brand", pencil.getBrand()),
                Updates.set("color", pencil.getColor()),
                Updates.set("price", pencil.getPrice())
        );
        collection.updateOne(filter, updates);
    }

    public void delete(String id) {
        collection.deleteOne(Filters.eq("id", id));
    }
}