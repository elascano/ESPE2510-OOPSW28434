package controller;

import com.mongodb.client.*;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import model.Product;
import java.util.List;
import java.util.ArrayList;


public class ProductController {

    private final String CONNECTION_STRING = "mongodb+srv://Bryan:Bryan2000@cluster0.sx9cpnq.mongodb.net/?appName=Cluster0";
    private final String DB_NAME = "ProductDB";
    private final String COLLECTION_NAME = "product";

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> productCollection;

    // Constructor
    public ProductController() {
        mongoClient = MongoClients.create(CONNECTION_STRING);
        database = mongoClient.getDatabase(DB_NAME);
        productCollection = database.getCollection(COLLECTION_NAME);
    }

    // Convierte Product a Document
    private Document productToDocument(Product product) {
        return new Document("name", product.getName())
                .append("basePrice", product.getBasePrice())
                .append("priceWithVAT", product.getPriceWithVAT())
                .append("stock", product.getStock())
                .append("status", product.getStatus());
    }

    // Guarda un producto (si existe, actualiza; si no, inserta)
public void saveOrUpdateProduct(Product product) {
    if (product.getId() == null) {
        // Insertar
        Document doc = productToDocument(product);
        productCollection.insertOne(doc);
        product.setId(doc.getObjectId("_id")); // guarda el _id asignado
    } else {
        // Actualizar
        Document filter = new Document("_id", product.getId());
        Document update = new Document("$set", productToDocument(product));
        productCollection.updateOne(filter, update);
    }
}


    // Borra un producto por nombre
    public void deleteProduct(Product product) {
    productCollection.deleteOne(new Document("_id", product.getId()));
    }


    // Obtiene todos los productos desde MongoDB
    public List<Product> getProductsFromDB() {
    List<Product> products = new ArrayList<>();
    for (Document doc : productCollection.find()) {
        Product p = new Product(
            doc.getString("name"),
            doc.getDouble("basePrice"),
            doc.getDouble("priceWithVAT"),
            doc.getInteger("stock")
        );
        p.setId(doc.getObjectId("_id")); // 🔥 clave MongoDB
        p.setStatus(doc.getString("status"));
        products.add(p);
    }
    return products;
    }


    // Cierra la conexión con MongoDB
    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
    
    public void saveProduct(Product product) {
    Document doc = productToDocument(product);
    productCollection.insertOne(doc);
}

    
}
