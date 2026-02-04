package ec.edu.espe.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public final class MongoInventoryRepository {
    private static volatile MongoInventoryRepository instance;
    private final MongoCollection<Document> collection;

    private MongoInventoryRepository() {
        String uri = System.getenv("mongodb+srv://Steven:Steven2001@cluster0.mp8muds.mongodb.net/?appName=Cluster0");
        if (uri == null || uri.isBlank()) {
            throw new IllegalStateException("Defina la variable de entorno MONGO_URI.");
        }
        MongoClient client = MongoClients.create(uri);
        MongoDatabase database = client.getDatabase("inventory");
        collection = database.getCollection("shoes");
    }

    public static MongoInventoryRepository getInstance() {
        if (instance == null) {
            synchronized (MongoInventoryRepository.class) {
                if (instance == null) {
                    instance = new MongoInventoryRepository();
                }
            }
        }
        return instance;
    }

    public void addShoe(String id, String name, int stock) {
        collection.updateOne(
                Filters.eq("id", id),
                Updates.combine(
                        Updates.set("name", name),
                        Updates.set("stock", stock),
                        Updates.setOnInsert("id", id)
                ),
                new UpdateOptions().upsert(true)
        );
    }

    public Shoe findById(String id) {
        Document doc = collection.find(Filters.eq("id", id)).first();
        if (doc == null) {
            return null;
        }
        String name = doc.getString("name");
        int stock = doc.getInteger("stock", 0);
        return new Shoe(id, name, stock);
    }

    public int buy(String id, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Cantidad invalida.");
        }
        Document doc = collection.find(Filters.eq("id", id)).first();
        if (doc == null) {
            return -1;
        }
        int stock = doc.getInteger("stock", 0);
        if (stock < quantity) {
            return -2;
        }
        collection.updateOne(Filters.eq("id", id), Updates.inc("stock", -quantity));
        return stock - quantity;
    }
}
