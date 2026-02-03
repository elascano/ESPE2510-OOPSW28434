/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.Product.model;

import Utils.MongoDbConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.util.ArrayList;

public class ProductDao {

    private static int lastId = 0;
    private final MongoCollection<Document> collection;

    public ProductDao() {
        this.collection = MongoDbConnection.getCollection("ProductsJava");
        initializeLastId();
    }

    // ================= ID AUTOINCREMENTAL =================
    private void initializeLastId() {
        MongoCursor<Document> cursor = collection.find().iterator();
        int maxId = 0;

        while (cursor.hasNext()) {
            Document doc = cursor.next();
            if (doc.containsKey("id")) {
                int currentId = doc.getInteger("id");
                if (currentId > maxId) {
                    maxId = currentId;
                }
            }
        }
        lastId = maxId;
    }

    private int generateId() {
        lastId++;
        return lastId;
    }

    // ================= CREATE =================
    public void insert(Product product) {
        int newId = generateId();
        product.setId(newId);

        Document doc = new Document()
                .append("id", product.getId())
                .append("name", product.getName())
                .append("quantity", product.getQuantity())
                .append("price", product.getPrice())
                .append("subtotal", product.getSubtotal())
                .append("iva", product.getIva())
                .append("total", product.getTotal());

        collection.insertOne(doc);
        System.out.println("Product saved in MongoDB! ID: " + newId);
    }

    // ================= READ =================
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();
            Product product = new Product();
            product.setId(doc.getInteger("id"));
            product.setName(doc.getString("name"));
            product.setQuantity(doc.getInteger("quantity"));
            product.setPrice(doc.getDouble("price"));
            product.calculateValues();
            products.add(product);
        }
        return products;
    }

    // ================= UPDATE =================
    public void update(Product product) {
        Document updated = new Document("$set",
                new Document("name", product.getName())
                        .append("quantity", product.getQuantity())
                        .append("price", product.getPrice())
                        .append("subtotal", product.getSubtotal())
                        .append("iva", product.getIva())
                        .append("total", product.getTotal())
        );

        collection.updateOne(
                new Document("id", product.getId()),
                updated
        );
    }

    // ================= DELETE =================
    public void delete(int id) {
        collection.deleteOne(new Document("id", id));
    }
}
