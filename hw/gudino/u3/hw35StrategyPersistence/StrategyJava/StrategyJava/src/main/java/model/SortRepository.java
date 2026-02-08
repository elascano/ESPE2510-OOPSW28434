package model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class SortRepository {

    private MongoCollection<Document> collection;

    public SortRepository() {
        MongoClient client = MongoClients.create(
            "mongodb+srv://Bryan:B2000@cluster0.sx9cpnq.mongodb.net/"
        );
        MongoDatabase db = client.getDatabase("Prueba");
        collection = db.getCollection("Pruebita");
    }

    public void save(int[] unsorted, int[] sorted, String algorithm) {
        Document doc = new Document()
                .append("unsorted", arrayToString(unsorted))
                .append("size", unsorted.length)
                .append("algorithm", algorithm)
                .append("sorted", arrayToString(sorted));

        collection.insertOne(doc);
    }

    private String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) sb.append(",");
        }
        return sb.toString();
    }
}
