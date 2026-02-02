package ec.edu.espe.crudstrategy.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
import java.util.List;

public class CrudContext {

    private AddStrategy addStrategy;
    private DeleteStrategy deleteStrategy;
    private UpdateStrategy updateStrategy;
    private ReadStrategy readStrategy;

    public CrudContext(String storageType) {
        CrudStrategy storageStrategy = createStorageStrategy(storageType);
        this.addStrategy = new AddStrategy(storageStrategy);
        this.deleteStrategy = new DeleteStrategy(storageStrategy);
        this.updateStrategy = new UpdateStrategy(storageStrategy);
        this.readStrategy = new ReadStrategy(storageStrategy);
    }

    private CrudStrategy createStorageStrategy(String type) {
        switch (type.toUpperCase()) {
            case "JSON":
                return new JsonStrategy();
            case "CSV":
                return new CsvStrategy();
            case "MONGO":
            case "MONGODB":
                return new MongoStrategy();
            default:
                throw new IllegalArgumentException("Unsupported storage type: " + type);
        }
    }

    public void setStorageStrategy(String storageType) {
        CrudStrategy storageStrategy = createStorageStrategy(storageType);
        this.addStrategy.setStorageStrategy(storageStrategy);
        this.deleteStrategy.setStorageStrategy(storageStrategy);
        this.updateStrategy.setStorageStrategy(storageStrategy);
        this.readStrategy.setStorageStrategy(storageStrategy);
    }

    public boolean addCustomer(Customer customer) {
        return addStrategy.execute(customer);
    }

    public boolean deleteCustomer(int id) {
        return deleteStrategy.execute(id);
    }

    public boolean updateCustomer(int id, Customer customer) {
        return updateStrategy.execute(id, customer);
    }

    public List<Customer> getAllCustomers() {
        return readStrategy.execute();
    }

    public Customer getCustomerById(int id) {
        return readStrategy.executeById(id);
    }

    public String getCurrentStorageType() {
        return addStrategy.getStorageFormatName();
    }
}
