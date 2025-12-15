package ec.edu.espe.students.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    private static final String URI =
            "mongodb+srv://Joseph:Joseph1751774793@cluster0.h8pi0ir.mongodb.net/";

    private static MongoClient client;

    public static MongoDatabase getDatabase() {
        if (client == null) {
            client = MongoClients.create(URI);
        }
        return client.getDatabase("studentsDB");
    }
}
