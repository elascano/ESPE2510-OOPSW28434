package ec.edu.espe.contactstbook.controller;

/**
 *
 * @author Josue Rojas
 */

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    
    

    private static final String URI = "mongodb+srv://Josue:Josue2006@cluster0.da07rsq.mongodb.net/?appName=Cluster0";
        
    private static final String DATABASE = "ConectionMongoDB";

    private static MongoClient mongoClient = null;

    // Obtener conexión
    public static MongoDatabase getConnection() {
        try {
            if (mongoClient == null) {
                mongoClient = MongoClients.create(URI);
                System.out.println("MongoDB conectado correctamente.");
            }
            return mongoClient.getDatabase(DATABASE);

        } catch (Exception e) {
            System.err.println("Error al conectar con MongoDB: " + e.getMessage());
            return null;
        }
    }
}

    

