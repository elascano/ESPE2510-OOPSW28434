package ec.edu.espe.galeryartsculpture.controller;

import ec.edu.espe.galeryartsculpture.model.Sculpture;
import ec.edu.espe.galeryartsculpture.utils.MongoConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class SculptureController {

    private MongoCollection<Document> collection;

    // --- [REUSABLE] CONSTANTES DE NEGOCIO ---
    private static final double IVA_RATE = 0.15;

    public SculptureController() {
        MongoDatabase db = MongoConnection.getInstance().getDatabase();
        this.collection = db.getCollection("sculptures"); // Nombre de colección actualizado
    }

    // ================================================================
    //  SECCIÓN DE LÓGICA DE NEGOCIO (BUSINESS RULES)
    //  [SRP]: Esta sección solo calcula, no guarda en BD.
    //  [EXAMEN]: Si te piden calcular descuentos, crea un método aquí.
    // ================================================================
    public double calculateIva(double price) {
        // [A] Abstraction: Ocultamos la fórmula matemática compleja aquí
        double result = price * (1 + IVA_RATE);
        return Math.round(result * 100.0) / 100.0;
    }

    // ================================================================
    //  SECCIÓN DE PERSISTENCIA (CRUD)
    //  [SRP]: Esta sección solo habla con MongoDB.
    // ================================================================
    // --- CREATE ---
    public boolean createSculpture(String id, String name, double price, List<String> materials) {
        try {
            // 1. Aplicar Regla de Negocio
            double finalPrice = calculateIva(price);

            // 2. Crear Documento (Mapeo)
            Document doc = new Document("id", id)
                    .append("name", name)
                    .append("price", price)
                    .append("materials", materials) // Guardamos la lista
                    .append("priceWithIva", finalPrice);

            // 3. Insertar en BD
            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            System.err.println("Error creating: " + e.getMessage());
            return false;
        }
    }

    // --- READ (ALL) ---
    public List<Sculpture> getAllSculptures() {
        List<Sculpture> list = new ArrayList<>();
        for (Document doc : collection.find()) {
            list.add(mapDocumentToSculpture(doc));
        }
        return list;
    }

    // --- READ (BY ID) ---
    public Sculpture findSculptureById(String id) {
        Document doc = collection.find(Filters.eq("id", id)).first();
        if (doc != null) {
            return mapDocumentToSculpture(doc);
        }
        return null;
    }

    // --- UPDATE ---
    public boolean updateSculpture(String id, String name, double price, List<String> materials) {
        try {
            // 1. Recalcular Regla de Negocio (Importante al actualizar)
            double newPriceWithIva = calculateIva(price);

            // 2. Enviar a Mongo
            UpdateResult result = collection.updateOne(
                    Filters.eq("id", id),
                    Updates.combine(
                            Updates.set("name", name),
                            Updates.set("price", price),
                            Updates.set("materials", materials),
                            Updates.set("priceWithIva", newPriceWithIva)
                    )
            );
            return result.getMatchedCount() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // --- DELETE ---
    public boolean deleteSculpture(String id) {
        try {
            DeleteResult result = collection.deleteOne(Filters.eq("id", id));
            return result.getDeletedCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // --- [M] MODULARITY: HELPER MAPPER ---
    // Convierte el documento feo de Mongo en un Objeto Sculpture bonito
    private Sculpture mapDocumentToSculpture(Document doc) {
        String id = doc.getString("id");
        if (id == null) {
            id = doc.getObjectId("_id").toString();
        }

        String name = doc.getString("name");
        double price = doc.getDouble("price");

        // Mapeo seguro de lista
        List<String> materials = doc.getList("materials", String.class);
        if (materials == null) {
            materials = new ArrayList<>();
        }

        Double storedIva = doc.getDouble("priceWithIva");
        if (storedIva == null) {
            storedIva = calculateIva(price); // Fallback si es null
        }
        return new Sculpture(id, name, price, materials, storedIva);
    }
}
