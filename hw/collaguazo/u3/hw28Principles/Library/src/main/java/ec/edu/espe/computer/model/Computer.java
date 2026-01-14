package ec.edu.espe.computer.model;

import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Pablo Collaguazo
 */
public class Computer implements ILibrary{

    private final MongoCollection<Document> collection;

    public Computer() {
        this.collection = Database.getConnection().getCollection("ComputersJava");
    }
    
    @Override
    public void insert(Document data) {
        collection.insertOne(data);
    }
    @Override
    public List<Document> getAll() {
        return collection.find().into(new ArrayList<>());
    }

}
