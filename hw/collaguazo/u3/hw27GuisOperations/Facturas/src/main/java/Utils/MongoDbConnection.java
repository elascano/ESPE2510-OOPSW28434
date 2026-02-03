/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDbConnection {

    private static final String URI = "mongodb+srv://Psblo:Pablo2006@cluster0.cadn1kx.mongodb.net/?retryWrites=true&w=majority";
    private static final String DB_NAME = "factura_db";

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    // 🔹 Conectar a MongoDB
    private static void connect() {
        try {
            if (mongoClient == null) {
                mongoClient = MongoClients.create(URI);
            }
            if (database == null) {
                database = mongoClient.getDatabase(DB_NAME);
            }
            System.out.println("Conectado a MongoDB - " + DB_NAME);
        } catch (Exception e) {
            System.out.println("Error al conectar a MongoDB: " + e.getMessage());
        }
    }

    // 🔹 Obtener base de datos
    public static MongoDatabase getDatabase() {
        if (database == null) {
            connect();
        }
        return database;
    }

    // 🔹 Obtener colección
    public static MongoCollection<Document> getCollection(String collectionName) {
        if (database == null) {
            connect();
        }
        return database.getCollection(collectionName);
    }

    // 🔹 Cerrar cliente al terminar la aplicación
    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
            database = null;
            System.out.println("Conexión a MongoDB cerrada");
        }
    }
}
