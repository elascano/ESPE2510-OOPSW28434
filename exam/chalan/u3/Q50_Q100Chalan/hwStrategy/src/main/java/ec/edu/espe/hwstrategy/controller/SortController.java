package ec.edu.espe.hwstrategy.controller;

import com.mongodb.client.MongoCollection;
import ec.edu.espe.hwstrategy.model.SortingContext;
import org.bson.Document;
import utils.MongoDBConnection;
import java.util.Arrays;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
public class SortController {
    
    private SortingContext context;

    public SortController() {
        this.context = new SortingContext();
    }

    public int[] sortData(int[] data) {
        return context.sort(data);
    }

    public void saveToMongo(int[] unsorted, int[] sorted) {
        MongoCollection<Document> collection = MongoDBConnection.getDatabase().getCollection("arraySort");
        
        String algorithm = (unsorted.length > 0 && unsorted.length < 30) ? "BubbleSort" : "QuickSort";

        Document doc = new Document("unsorted", Arrays.toString(unsorted))
                .append("size", unsorted.length)
                .append("algorithm", algorithm)
                .append("sorted", Arrays.toString(sorted));

        collection.insertOne(doc);
    }
}