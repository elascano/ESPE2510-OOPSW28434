/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.Notebook.model;
import Utils.MongoDbConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.util.ArrayList;

/**
 * @author Pablo Collaguazo
 */
public class NotebookDao {

    private static int lastId = 0;
    private final MongoCollection<Document> collection;

    public NotebookDao() {
        this.collection = MongoDbConnection.getCollection("NotebooksJava");
        initializeLastId();
    }

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
        return ++lastId;
    }

    
    public void insert(Notebook notebook) {
        notebook.setId(generateId());

        Document doc = new Document()
                .append("id", notebook.getId())
                .append("name", notebook.getName())
                .append("quantity", notebook.getQuantity())
                .append("price", notebook.getPrice())
                .append("subtotal", notebook.getSubtotal())
                .append("iva", notebook.getIva())
                .append("total", notebook.getTotal());

        collection.insertOne(doc);
        System.out.println("Notebook saved in MongoDB! ID: " + notebook.getId());
    }

    public ArrayList<Notebook> getAllNotebooks() {
        ArrayList<Notebook> notebooks = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();
            Notebook notebook = new Notebook();

            notebook.setId(doc.getInteger("id"));
            notebook.setName(doc.getString("name"));
            notebook.setQuantity(doc.getInteger("quantity"));
            notebook.setPrice(doc.getDouble("price"));

            notebook.calculateValues();
            notebooks.add(notebook);
        }
        return notebooks;
    }

    
    public void update(Notebook notebook) {
        Document updated = new Document("$set",
                new Document("name", notebook.getName())
                        .append("quantity", notebook.getQuantity())
                        .append("price", notebook.getPrice())
                        .append("subtotal", notebook.getSubtotal())
                        .append("iva", notebook.getIva())
                        .append("total", notebook.getTotal())
        );

        collection.updateOne(
                new Document("id", notebook.getId()),
                updated
        );
    }

    public void delete(int id) {
        collection.deleteOne(new Document("id", id));
    }
}


