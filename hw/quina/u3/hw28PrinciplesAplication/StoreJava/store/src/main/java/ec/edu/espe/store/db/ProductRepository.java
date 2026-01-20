package ec.edu.espe.store.db;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import ec.edu.espe.store.model.Product;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public class ProductRepository {

    private final MongoCollection<Document> collection;

    public ProductRepository(String uri, String dbName, String collectionName) {
        MongoDatabase database = MongoClients.create(uri).getDatabase(dbName);
        this.collection = database.getCollection(collectionName);
    }

    public void create(Product product) {
        Document doc = new Document("id", product.getId())
                .append("name", product.getName())
                .append("basePrice", product.getBasePrice())
                .append("finalPrice", product.getFinalPrice());
        collection.insertOne(doc);
    }

    public List<Product> readAll() {
        List<Product> products = new ArrayList<>();
        for (Document doc : collection.find()) {
            Product p = new Product(doc.getString("id"), doc.getString("name"), doc.getDouble("basePrice"));
            p.setFinalPrice(doc.getDouble("finalPrice"));
            products.add(p);
        }
        return products;
    }

    public Product findById(String id) {
        Document doc = collection.find(Filters.eq("id", id)).first();
        if (doc != null) {
            Product p = new Product(doc.getString("id"), doc.getString("name"), doc.getDouble("basePrice"));
            p.setFinalPrice(doc.getDouble("finalPrice"));
            return p;
        }
        return null;
    }

    public void update(Product product) {
        Document updateData = new Document("name", product.getName())
                .append("basePrice", product.getBasePrice())
                .append("finalPrice", product.getFinalPrice());
        collection.updateOne(Filters.eq("id", product.getId()), new Document("$set", updateData));
    }

    public void delete(String id) {
        collection.deleteOne(Filters.eq("id", id));
    }
}
