package ec.edu.espe.idstudentsregistration.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * SOLO CONEXIÓN
 * No pongas CRUD aquí, solo crea el cliente y entrega la DB.
 */
public class MongoConnection {

    // CAMBIA AQUÍ: pega tu URI de Mongo (Atlas o local)
    // Ejemplo local: "mongodb://localhost:27017"
    private static final String URI = "mongodb+srv://Joseph:Joseph1751774793@cluster0.h8pi0ir.mongodb.net/?appName=Cluster0";

    // CAMBIA AQUÍ: nombre de la base de datos
    private static final String DB_NAME = "students_db";

    private static MongoClient client;

    public static MongoDatabase getDatabase() {
        // patrón simple singleton
        if (client == null) {
            client = MongoClients.create(URI);
        }
        return client.getDatabase(DB_NAME);
    }

    public static void close() {
        if (client != null) {
            client.close();
            client = null;
        }
    }
}
