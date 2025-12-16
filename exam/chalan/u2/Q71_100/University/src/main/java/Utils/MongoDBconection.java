package Utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBconection {

    private static MongoClient client;

    public static MongoDatabase getDatabase() {
        if (client == null) {
            client = MongoClients.create(
                "mongodb+srv://kevin:kevin2001@cluster0.oxinj5p.mongodb.net/?retryWrites=true&w=majority"
            );
        }
        return client.getDatabase("universityDB");
    }
}
