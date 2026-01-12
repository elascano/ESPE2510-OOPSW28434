package ec.edu.espe.soccergui.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import ec.edu.espe.soccergui.utils.MongoDBConnection;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;

public class SimpleCrud {
    private MongoCollection<Document> collection;
    
    public SimpleCrud(String collectionName) {
        MongoDatabase database = MongoDBConnection.getConnection();
        this.collection = database.getCollection(collectionName);
        System.out.println("CRUD listo para colección: " + collectionName);
    }
    
    // ========== CREATE ==========
    public int create(Document document) {
        try {
            // Buscar el máximo ID
            int nextId = getNextId();
            document.put("id", nextId);
            
            collection.insertOne(document);
            System.out.println("✅ CREADO con ID: " + nextId);
            return nextId;
            
        } catch (Exception e) {
            System.err.println("❌ Error al crear: " + e.getMessage());
            return -1;
        }
    }
    
    // ========== READ ==========
    public Document read(int id) {
        try {
            Bson filter = Filters.eq("id", id);
            return collection.find(filter).first();
        } catch (Exception e) {
            System.err.println("❌ Error al leer ID " + id + ": " + e.getMessage());
            return null;
        }
    }
    
    public List<Document> readAll() {
        List<Document> documents = new ArrayList<>();
        try {
            for (Document doc : collection.find()) {
                documents.add(doc);
            }
        } catch (Exception e) {
            System.err.println("❌ Error al leer todos: " + e.getMessage());
        }
        return documents;
    }
    
    public List<Document> readAllSortedById() {
        List<Document> documents = new ArrayList<>();
        try {
            for (Document doc : collection.find().sort(new Document("id", 1))) {
                documents.add(doc);
            }
        } catch (Exception e) {
            System.err.println("❌ Error al leer ordenados: " + e.getMessage());
        }
        return documents;
    }
    
    // ========== UPDATE ==========
    public boolean update(int id, Document newData) {
        try {
            Bson filter = Filters.eq("id", id);
            Document result = collection.findOneAndReplace(filter, newData);
            boolean success = result != null;
            System.out.println(success ? "✅ ACTUALIZADO ID: " + id : "❌ No encontrado ID: " + id);
            return success;
        } catch (Exception e) {
            System.err.println("❌ Error al actualizar ID " + id + ": " + e.getMessage());
            return false;
        }
    }
    
    // ========== DELETE ==========
    public boolean delete(int id) {
        try {
            Bson filter = Filters.eq("id", id);
            long deleted = collection.deleteOne(filter).getDeletedCount();
            boolean success = deleted > 0;
            System.out.println(success ? "✅ ELIMINADO ID: " + id : "❌ No encontrado ID: " + id);
            return success;
        } catch (Exception e) {
            System.err.println("❌ Error al eliminar ID " + id + ": " + e.getMessage());
            return false;
        }
    }
    
    // ========== MÉTODOS ÚTILES ==========
    private int getNextId() {
        try {
            // Encontrar el máximo ID actual
            int maxId = 0;
            for (Document doc : collection.find()) {
                if (doc.containsKey("id")) {
                    int currentId = doc.getInteger("id");
                    if (currentId > maxId) {
                        maxId = currentId;
                    }
                }
            }
            return maxId + 1;
        } catch (Exception e) {
            return 1; // Si no hay documentos, empezar con 1
        }
    }
    
    public boolean exists(int id) {
        return read(id) != null;
    }
    
    public long count() {
        return collection.countDocuments();
    }
    
    // Búsquedas específicas
    public List<Document> findByField(String fieldName, Object value) {
        List<Document> results = new ArrayList<>();
        try {
            Bson filter = Filters.eq(fieldName, value);
            for (Document doc : collection.find(filter)) {
                results.add(doc);
            }
        } catch (Exception e) {
            System.err.println("❌ Error en búsqueda: " + e.getMessage());
        }
        return results;
    }
}