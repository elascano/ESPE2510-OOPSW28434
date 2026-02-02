package ec.edu.espe.crudstrategy.model;

/**
 *
 * @author Adrian Toapanta 
 */

import ec.edu.espe.crudstrategy.model.Customer;
import ec.edu.espe.crudstrategy.utils.MongoConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoStrategy implements CrudStrategy {
    private static final String COLLECTION_NAME = "customers";
    
    @Override
    public boolean add(Customer customer) {
        try {
            MongoDatabase database = MongoConnection.getDatabase();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
            
            Document doc = new Document("id", customer.getId())
                    .append("name", customer.getName())
                    .append("email", customer.getEmail())
                    .append("phone", customer.getPhone());
            
            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            System.err.println("Error adding customer to MongoDB: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean delete(int id) {
        try {
            MongoDatabase database = MongoConnection.getDatabase();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
            
            Bson filter = Filters.eq("id", id);
            DeleteResult result = collection.deleteOne(filter);
            
            return result.getDeletedCount() > 0;
        } catch (Exception e) {
            System.err.println("Error deleting from MongoDB: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean update(int id, Customer updatedCustomer) {
        try {
            MongoDatabase database = MongoConnection.getDatabase();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
            
            Bson filter = Filters.eq("id", id);
            Bson updates = Updates.combine(
                Updates.set("name", updatedCustomer.getName()),
                Updates.set("email", updatedCustomer.getEmail()),
                Updates.set("phone", updatedCustomer.getPhone())
            );
            
            UpdateResult result = collection.updateOne(filter, updates);
            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            System.err.println("Error updating in MongoDB: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public List<Customer> readAll() {
        List<Customer> customers = new ArrayList<>();
        try {
            MongoDatabase database = MongoConnection.getDatabase();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
            
            for (Document doc : collection.find()) {
                Customer customer = new Customer(
                    doc.getInteger("id"),
                    doc.getString("name"),
                    doc.getString("email"),
                    doc.getString("phone")
                );
                customers.add(customer);
            }
        } catch (Exception e) {
            System.err.println("Error reading from MongoDB: " + e.getMessage());
        }
        return customers;
    }
    
    @Override
    public Customer readById(int id) {
        try {
            MongoDatabase database = MongoConnection.getDatabase();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
            
            Bson filter = Filters.eq("id", id);
            Document doc = collection.find(filter).first();
            
            if (doc != null) {
                return new Customer(
                    doc.getInteger("id"),
                    doc.getString("name"),
                    doc.getString("email"),
                    doc.getString("phone")
                );
            }
        } catch (Exception e) {
            System.err.println("Error reading customer by ID from MongoDB: " + e.getMessage());
        }
        return null;
    }
    
    @Override
    public String getFormatName() {
        return "MongoDB";
    }
}
