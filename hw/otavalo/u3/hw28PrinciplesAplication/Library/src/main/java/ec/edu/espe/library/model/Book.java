package ec.edu.espe.library.model;

import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */
public class Book implements ILibrary{

    private final MongoCollection<Document> collection;

    public Book() {
        this.collection = Database.getConnection().getCollection("LibraryJava");
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
