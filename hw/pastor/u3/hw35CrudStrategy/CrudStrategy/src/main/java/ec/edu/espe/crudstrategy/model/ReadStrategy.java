package ec.edu.espe.crudstrategy.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
import ec.edu.espe.crudstrategy.model.Customer;
import java.util.List;

public class ReadStrategy {

    private CrudStrategy storageStrategy;

    public ReadStrategy(CrudStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public List<Customer> execute() {
        return storageStrategy.readAll();
    }

    public Customer executeById(int id) {
        return storageStrategy.readById(id);
    }

    public void setStorageStrategy(CrudStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public String getStorageFormatName() {
        return storageStrategy.getFormatName();
    }
}
