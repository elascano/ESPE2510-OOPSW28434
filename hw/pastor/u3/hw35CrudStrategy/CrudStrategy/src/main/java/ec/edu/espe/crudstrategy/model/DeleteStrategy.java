package ec.edu.espe.crudstrategy.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */

import ec.edu.espe.crudstrategy.model.Customer;
import java.util.List;

public class DeleteStrategy {
    private CrudStrategy storageStrategy;
    
    public DeleteStrategy(CrudStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }
    
    public boolean execute(int id) {
        Customer customer = storageStrategy.readById(id);
        if (customer == null) {
            System.err.println("Error: ID " + id + " not found in " + 
                             storageStrategy.getFormatName());
            return false;
        }
        
        return storageStrategy.delete(id);
    }
    
    public void setStorageStrategy(CrudStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }
    
    public String getStorageFormatName() {
        return storageStrategy.getFormatName();
    }
}
