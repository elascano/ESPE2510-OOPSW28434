/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.contacts.controller;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import utils.MongoConnection;

/**
 *
 * @author Mateo Cevallos
 */
public class IdGenerator {
     /**
     * Obtiene el próximo ID autoincremental para una colección
     * @param collectionName Nombre de la colección
     * @return Próximo ID disponible (1 si es el primero)
     */
    public static int getNextId(String collectionName) {
        MongoDatabase database = MongoConnection.getConnection();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        
        try {
            // Buscar el máximo ID actual usando agregación
            Document maxIdDoc = collection
                .aggregate(java.util.Arrays.asList(
                    new Document("$group", 
                        new Document("_id", null)
                            .append("maxId", 
                                new Document("$max", "$id"))
                    )
                ))
                .first();
            
            if (maxIdDoc != null && maxIdDoc.containsKey("maxId")) {
                int maxId = maxIdDoc.getInteger("maxId");
                return maxId + 1;
            } else {
                // No hay documentos aún, empezar con 1
                return 1;
            }
            
        } catch (Exception e) {
            System.err.println("Error al generar ID: " + e.getMessage());
            // Fallback: contar documentos + 1
            long count = collection.countDocuments();
            return (int) count + 1;
        }
    }
    
    /**
     * Verifica si un ID ya existe en la colección
     */
    public static boolean idExists(String collectionName, int id) {
        MongoDatabase database = MongoConnection.getConnection();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        
        Document query = new Document("id", id);
        return collection.countDocuments(query) > 0;
    }
    
    /**
     * Encuentra el primer ID disponible (para reutilizar huecos)
     */
    public static int findFirstAvailableId(String collectionName) {
        MongoDatabase database = MongoConnection.getConnection();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        
        // Obtener todos los IDs ordenados
        java.util.List<Integer> ids = new java.util.ArrayList<>();
        
        for (Document doc : collection.find().sort(new Document("id", 1))) {
            if (doc.containsKey("id")) {
                ids.add(doc.getInteger("id"));
            }
        }
        
        // Si no hay IDs, empezar con 1
        if (ids.isEmpty()) {
            return 1;
        }
        
        // Buscar el primer hueco
        for (int i = 1; i <= ids.get(ids.size() - 1); i++) {
            if (!ids.contains(i)) {
                return i;
            }
        }
        
        // Si no hay huecos, siguiente al máximo
        return ids.get(ids.size() - 1) + 1;
    }
}
