package ec.edu.espe.product.utils;

import com.mongodb.client.*;
import ec.edu.espe.product.model.Product;
import org.bson.Document;
import java.util.*;
/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */

public class MongoDBManager {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public MongoDBManager() {
        mongoClient = MongoClients.create(Config.CONNECTION_STRING);
        database = mongoClient.getDatabase(Config.DATABASE_NAME);
        collection = database.getCollection(Config.COLLECTION_NAME);
    }

    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();

        for (Document doc : collection.find()) {
            list.add(documentToEntity(doc));
        }
        return list;
    }

    public Product findById(String id) {
        Document doc = collection.find(
                new Document(Config.ID_FIELD, id)
        ).first();

        if (doc == null) {
            return null;
        }
        return documentToEntity(doc);
    }

    public boolean insert(Product entity) {
        if (findById(entity.getId()) != null) {
            return false;
        }
        collection.insertOne(entityToDocument(entity));
        return true;
    }

    public boolean update(String id, Product entity) {
        Document updateDoc = new Document("$set", entityToDocument(entity));
        return collection.updateOne(
                new Document(Config.ID_FIELD, id),
                updateDoc
        ).getModifiedCount() > 0;
    }

    public boolean delete(String id) {
        return collection.deleteOne(
                new Document(Config.ID_FIELD, id)
        ).getDeletedCount() > 0;
    }

    private Product documentToEntity(Document doc) {
        String id = doc.getString(Config.ID_FIELD);
        doc.remove(Config.ID_FIELD);
        return new Product(id, new HashMap<>(doc));
    }

    private Document entityToDocument(Product entity) {
        Document doc = new Document();
        doc.put(Config.ID_FIELD, entity.getId());
        doc.putAll(entity.getAttributes());
        return doc;
    }
    public String getNextId() {
        int maxId = 0;

        for (Document doc : collection.find()) {
            String idStr = doc.getString(Config.ID_FIELD);
            if (idStr != null && idStr.matches("\\d+")) {
                int id = Integer.parseInt(idStr);
                if (id > maxId) {
                    maxId = id;
                }
            }
        }
        return String.valueOf(maxId + 1);
    }
}