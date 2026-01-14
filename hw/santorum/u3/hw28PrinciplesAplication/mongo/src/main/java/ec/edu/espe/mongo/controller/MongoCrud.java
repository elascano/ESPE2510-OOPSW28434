package ec.edu.espe.mongo.controller;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import ec.edu.espe.mongo.model.Toy;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Thais Santorum
 */
public class MongoCrud {

    MongoDatabase db = MongoConnection.getDatabase();
    MongoCollection<Document> collection = db.getCollection("toys");

    public void create(Toy toy) {

        float iva = toy.calculatePriceIva();

        Document doc = new Document("id", toy.getId())
                .append("name", toy.getName())
                .append("price", toy.getPrice())
                .append("priceIva", iva);

        collection.insertOne(doc);
    }

    public Toy readById(int id) {

        Document d = collection.find(eq("id", id)).first();

        if (d == null) {
            return null;
        }

        Number priceNumber = d.get("price", Number.class);
        Number priceIvaNumber = d.get("priceIva", Number.class);

        return new Toy(
                d.getInteger("id"),
                d.getString("name"),
                priceNumber.floatValue(),
                priceIvaNumber.floatValue()
        );
    }

    public void update(Toy toy) {

        float iva = toy.calculatePriceIva();

        Document updated = new Document("$set",
                new Document("name", toy.getName())
                        .append("price", toy.getPrice())
                        .append("priceIva", iva)
        );

        collection.updateOne(eq("id", toy.getId()), updated);
    }

    public void delete(int id) {
        collection.deleteOne(eq("id", id));
    }

    public List<Toy> readAll() {

        List<Toy> toys = new ArrayList<>();

        FindIterable<Document> documents = collection.find();

        for (Document d : documents) {

            Number priceNumber = d.get("price", Number.class);
            Number priceIvaNumber = d.get("priceIva", Number.class);

            Toy toy = new Toy(
                    d.getInteger("id"),
                    d.getString("name"),
                    priceNumber.floatValue(),
                    priceIvaNumber.floatValue()
            );

            toys.add(toy);
        }

        return toys;
    }
}
