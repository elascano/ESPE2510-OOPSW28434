package ec.edu.espe.practica.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private MongoCollection<Document> collection;

    public ProductController() {
        // Tu URI de Atlas
        String uri = "mongodb+srv://Josue:Josue2006@cluster0.da07rsq.mongodb.net/";
        MongoClient mongoClient = MongoClients.create(uri);
        
        // USANDO TUS NOMBRES DE LA IMAGEN: tienda_db y productos
        MongoDatabase database = mongoClient.getDatabase("tienda_db");
        this.collection = database.getCollection("productos");
    }

    public void guardar(String id, String desc, double precioBase) {
        // Cálculo del 15% antes de enviar a la nube
        double precioTotal = precioBase * 1.15;
        
        Document doc = new Document("_id", id)
                .append("description", desc)
                .append("tax_included_price", precioTotal);
        
        collection.insertOne(doc);
    }

    public List<Document> obtenerTodos() {
        return collection.find().into(new ArrayList<>());
    }
    // Método para ELIMINAR
    public void eliminar(String id) {
        collection.deleteOne(new org.bson.Document("_id", id));
    }

    // Método para ACTUALIZAR
    public void actualizar(String id, String nuevaDesc, double nuevoPrecioBase) {
        double nuevoTotal = nuevoPrecioBase * 1.15; // Volvemos a calcular el IVA
        
        org.bson.Document filtro = new org.bson.Document("_id", id);
        org.bson.Document nuevosDatos = new org.bson.Document("$set", new org.bson.Document("description", nuevaDesc)
                                            .append("tax_included_price", nuevoTotal));
        
        collection.updateOne(filtro, nuevosDatos);
    }
}