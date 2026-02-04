package ec.edu.espe.inventory.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import ec.edu.espe.inventory.model.Product;

import static com.mongodb.client.model.Filters.*;

public final class InventoryController {

    
    private static final String CONNECTION_STRING = "mongodb+srv://Steven:Steven2001@cluster0.mp8muds.mongodb.net/?appName=Cluster0t";
    private static final String DB_NAME = "inventory_db";
    private static final String COLLECTION_PRODUCTS = "products";
    
    private final MongoCollection<Document> products;

    public InventoryController() {
        MongoDatabase db = MongoClientSingleton.getInstance()
                .getDatabase(DB_NAME);

       
        this.products = db.getCollection(COLLECTION_PRODUCTS);
    }

 
    private static final class MongoClientSingleton {
        private static MongoClient instance;

        private MongoClientSingleton() { }  

        public static synchronized MongoClient getInstance() {
            if (instance == null) {
                instance = MongoClients.create(CONNECTION_STRING);
            }
            return instance;
        }
    }
    

    public void addProduct(Product product) {
        Document doc = new Document("_id", product.getId())
                .append("name", product.getName())
                .append("stock", product.getStock());

        
        products.insertOne(doc);
    }

    public Product findById(String idRaw) {
        Bson filter = buildIdFilter(idRaw);
        Document doc = products.find(filter).first();
        return (doc == null) ? null : toProduct(doc);
    }

    
    public Product sell(String idRaw, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Cantidad a vender debe ser > 0");

       
        Bson idFilter = buildIdFilter(idRaw);

  
        Bson filterWithStock = and(idFilter, gte("stock", quantity));

        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions()
                .returnDocument(ReturnDocument.AFTER);

        Document updated = products.findOneAndUpdate(
                filterWithStock,
                Updates.inc("stock", -quantity),
                options
        );

        if (updated != null) {
            return toProduct(updated);
        }

      
        Document existing = products.find(idFilter).first();
        if (existing == null) return null;

        int currentStock = existing.getInteger("stock", 0);
        throw new IllegalStateException(
                "Stock insuficiente. Stock actual: " + currentStock + ", intentaste vender: " + quantity
        );
    }

    
    private Bson buildIdFilter(String idRaw) {
        Object id = parseId(idRaw);
        
        if (id instanceof Integer intId) {
            return or(eq("_id", intId), eq("_id", idRaw));
        }
        return or(eq("_id", idRaw), eq("_id", tryParseIntOrNull(idRaw)));
    }

    private Object parseId(String idRaw) {
        if (idRaw == null || idRaw.trim().isEmpty()) {
            throw new IllegalArgumentException("Id es requerido");
        }
        String trimmed = idRaw.trim();
        Integer intId = tryParseIntOrNull(trimmed);
        return (intId != null) ? intId : trimmed;
    }

    private Integer tryParseIntOrNull(String s) {
        try { return Integer.parseInt(s.trim()); }
        catch (Exception e) { return null; }
    }

    private Product toProduct(Document doc) {
        Object id = doc.get("_id");
        String name = doc.getString("name");
        int stock = doc.getInteger("stock", 0);
        return new Product(id, name, stock);
    }
}

