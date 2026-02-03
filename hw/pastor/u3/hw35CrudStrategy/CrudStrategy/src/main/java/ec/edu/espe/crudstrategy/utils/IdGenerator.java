package ec.edu.espe.crudstrategy.utils;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
import ec.edu.espe.crudstrategy.model.*;
import java.util.List;

public class IdGenerator {
    public static int generateUniqueId() {
        JsonStrategy jsonStrategy = new JsonStrategy();
        CsvStrategy csvStrategy = new CsvStrategy();
        MongoStrategy mongoStrategy = new MongoStrategy();
        
        List<Customer> jsonCustomers = jsonStrategy.readAll();
        List<Customer> csvCustomers = csvStrategy.readAll();
        List<Customer> mongoCustomers = mongoStrategy.readAll();
        
        int maxId = 0;
        
        for (Customer c : jsonCustomers) {
            if (c.getId() > maxId) maxId = c.getId();
        }
        
        for (Customer c : csvCustomers) {
            if (c.getId() > maxId) maxId = c.getId();
        }
        
        for (Customer c : mongoCustomers) {
            if (c.getId() > maxId) maxId = c.getId();
        }
        
        return maxId + 1;
    }
}
