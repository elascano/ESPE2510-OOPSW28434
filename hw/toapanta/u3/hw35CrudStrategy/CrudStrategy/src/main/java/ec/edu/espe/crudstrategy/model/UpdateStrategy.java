package ec.edu.espe.crudstrategy.model;

/**
 *
 *@author Adrian Toapanta 
 */

import ec.edu.espe.crudstrategy.model.Customer;
import java.util.List;

public class UpdateStrategy {
     private CrudStrategy storageStrategy;
    
    public UpdateStrategy(CrudStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }
    
    public boolean execute(int id, Customer updatedCustomer) {
        Customer existingCustomer = storageStrategy.readById(id);
        if (existingCustomer == null) {
            System.err.println("Error: ID " + id + " not found in " + 
                             storageStrategy.getFormatName());
            return false;
        }
        
        updatedCustomer.setId(id);
        
        return storageStrategy.update(id, updatedCustomer);
    }
    
    public void setStorageStrategy(CrudStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }
    
    public String getStorageFormatName() {
        return storageStrategy.getFormatName();
    }
}
