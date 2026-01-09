package ec.edu.espe.actor.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import ec.edu.espe.actor.model.Customer;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.FindIterable;
import java.util.ArrayList;

/**
 *
 * @author Thais Santorum
 */
public class MongoCrud {

    MongoDatabase db = MongoConnection.getDatabase();
    MongoCollection<Document> collection = db.getCollection("customers");


 


    public List<Customer> readAll() {

        List<Customer> customers = new ArrayList<>();

        FindIterable<Document> documents = collection.find();

            for (Document doc : documents) {
                Customer customer = new Customer();

                customer.setId(doc.getInteger("id"));
                customer.setFullName(doc.getString("fullName"));
                customer.setEmail(doc.getString("email"));
                customer.setType(doc.getString("type"));
                customer.setDiscount(doc.getInteger("discount"));
                customer.setTotalSale(doc.getInteger("totalSale"));

                customers.add(customer);
            }

        return customers;

    }
    
    
    
    
}
