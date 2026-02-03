package ec.edu.espe.crudstrategy.model;

/**
 *
 * @author Adrian Toapanta 
 */

import ec.edu.espe.crudstrategy.model.Customer;
import java.util.List;

public class AddStrategy {
    private CrudStrategy storageStrategy;
    
    public AddStrategy(CrudStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }
    
    public boolean execute(Customer customer) {
        List<Customer> existingCustomers = storageStrategy.readAll();
        boolean idExists = existingCustomers.stream()
                .anyMatch(c -> c.getId() == customer.getId());
        
        if (idExists) {
            System.err.println("Error: ID " + customer.getId() + " already exists in " + 
                             storageStrategy.getFormatName());
            return false;
        }
        
        return storageStrategy.add(customer);
    }
    
    public void setStorageStrategy(CrudStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }
    
    public String getStorageFormatName() {
        return storageStrategy.getFormatName();
    }
}
