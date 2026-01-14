package ec.edu.espe.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.model.Product;
import org.bson.Document;

public class MongoProductRepository implements ProductRepository {
    private final MongoCollection<Document> collection;

    public MongoProductRepository() {
        String connectionString = "mongodb+srv://Emily:Emily2006@cluster0.ynnit6l.mongodb.net/";
        
        MongoClient mongoClient = MongoClients.create(connectionString);
        
        MongoDatabase database = mongoClient.getDatabase("ComputerStoreDB");
        
        this.collection = database.getCollection("products");
    }

    @Override
    public void save(Product product) {
        Document doc = new Document("name", product.getName())
                .append("basePrice", product.getBasePrice())
                .append("totalPrice", product.getTotalPrice())
                .append("taxRate", "15%"); 
        
        collection.insertOne(doc);
    }
}