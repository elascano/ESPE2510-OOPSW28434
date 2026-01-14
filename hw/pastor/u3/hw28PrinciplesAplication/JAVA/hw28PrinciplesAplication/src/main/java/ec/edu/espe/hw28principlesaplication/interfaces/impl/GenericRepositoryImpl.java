package ec.edu.espe.hw28principlesaplication.interfaces.impl;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.hw28principlesaplication.config.MongoConnection;
import ec.edu.espe.hw28principlesaplication.interfaces.IGenericRepository;
import ec.edu.espe.hw28principlesaplication.model.GenericEntity;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class GenericRepositoryImpl implements IGenericRepository<GenericEntity> {
    
    private MongoDatabase database;
    private String collectionName;
    
    public GenericRepositoryImpl(String collectionName) {
        this.database = MongoConnection.getInstance().getDatabase();
        this.collectionName = collectionName;
    }
    
    @Override
    public void create(GenericEntity entity) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(entity.toDocument());
    }
    
    @Override
    public List<GenericEntity> readAllData() {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        List<GenericEntity> list = new ArrayList<>();
        
        for (Document doc : collection.find()) {
            list.add(new GenericEntity(doc, collectionName));
        }
        return list;
    }
    
    @Override
    public void update(String id, GenericEntity entity) {
        System.out.println("TODO");
    }

    @Override
    public void delete(String id) {
        System.out.println("TODO");
    }
}
