package ec.edu.espe.storefruit.controller;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.storefruit.model.Fruit;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import utils.MongoDBUtil;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class FruitController {

    private MongoCollection<Document> collection;

    public FruitController() {
        MongoDatabase db = MongoDBUtil.getDatabase();
        collection = db.getCollection("fruits");
    }

    public void addFruit(Fruit fruit) {
        Document doc = new Document("name", fruit.getName())
                .append("price", fruit.getPrice())
                .append("stock", fruit.getStock());
        collection.insertOne(doc);
    }

    public double buyFruit(String name, int quantity) {
        Document fruit = collection.find(eq("name", name)).first();

        if (fruit == null) {
            return -1;
        }

        int stock = fruit.getInteger("stock");
        double price = fruit.getDouble("price");

        if (quantity > stock) {
            return -2;
        }

        int newStock = stock - quantity;
        collection.updateOne(eq("name", name), set("stock", newStock));

        return price * quantity;
    }

    public void deleteFruit(String name) {
        collection.deleteOne(eq("name", name));
    }

    public void updateStock(String name, int newStock) {
        collection.updateOne(eq("name", name), set("stock", newStock));
    }

    public String[] getFruitNames() {
        return collection.find()
                .map(doc -> doc.getString("name"))
                .into(new ArrayList<>())
                .toArray(new String[0]);
    }

    public double getPriceByName(String name) {
        Document fruit = collection.find(eq("name", name)).first();
        if (fruit == null) {
            return 0;
        }
        return fruit.getDouble("price");
    }

    public int getStockByName(String name) {
        Document fruit = collection.find(eq("name", name)).first();
        if (fruit == null) {
            return 0;
        }
        return fruit.getInteger("stock");
    }

    public List<Fruit> getAllFruits() {
        List<Fruit> fruits = new ArrayList<>();

        for (Document doc : collection.find()) {
            fruits.add(new Fruit(
                    doc.getString("name"),
                    doc.getDouble("price"),
                    doc.getInteger("stock")
            ));
        }

        return fruits;
    }
}
