package ec.edu.espe.crudstrategy.controller;

/**
 *
 *@author Mathews Pastor, The POOwer Rangers Of Programming 
 */
import ec.edu.espe.crudstrategy.model.Customer;
import ec.edu.espe.crudstrategy.model.CrudContext;
import ec.edu.espe.crudstrategy.utils.IdGenerator;
import java.util.List;

public class CustomerController {

    private CrudContext crudContext;

    public CustomerController() {
        this.crudContext = new CrudContext("JSON");
    }

    public CustomerController(String storageType) {
        this.crudContext = new CrudContext(storageType);
    }

    public void setStorageType(String storageType) {
        this.crudContext.setStorageStrategy(storageType);
    }

    public String getCurrentStorageType() {
        return crudContext.getCurrentStorageType();
    }

    public Customer createCustomer(String name,
            String email, String phone) {
        String validationErrors = ValidationController.validateAllFields(
                name, email, phone);

        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException("Errores de validación:\n" + validationErrors);
        }

        int id = IdGenerator.generateUniqueId();

        return new Customer(id, name, email, phone);
    }

    public boolean addCustomer(Customer customer) {
        return crudContext.addCustomer(customer);
    }

    public boolean addCustomer(String name,
            String email, String phone) {
        try {
            Customer customer = createCustomer(name, email, phone);
            return addCustomer(customer);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("Error adding customer: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteCustomer(int id) {
        return crudContext.deleteCustomer(id);
    }

    public boolean updateCustomer(int id, String name,
            String email, String phone) {
        try {
            String validationErrors = ValidationController.validateAllFields(
                    name, email, phone);

            if (!validationErrors.isEmpty()) {
                throw new IllegalArgumentException("Errores de validación:\n" + validationErrors);
            }

            Customer updatedCustomer = new Customer(id, name, email, phone);
            return crudContext.updateCustomer(id, updatedCustomer);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("Error updating customer: " + e.getMessage());
            return false;
        }
    }

    public List<Customer> getAllCustomers() {
        return crudContext.getAllCustomers();
    }

    public Customer getCustomerById(int id) {
        return crudContext.getCustomerById(id);
    }

    public String[] getAllCustomersAsStringArray() {
        List<Customer> customers = getAllCustomers();
        String[] customerStrings = new String[customers.size()];

        for (int i = 0; i < customers.size(); i++) {
            customerStrings[i] = customers.get(i).toString();
        }

        return customerStrings;
    }

    public String getAllCustomersFormatted() {
        List<Customer> customers = getAllCustomers();
        StringBuilder sb = new StringBuilder();

        sb.append("=== CLIENTES (").append(getCurrentStorageType()).append(") ===\n");
        for (Customer customer : customers) {
            sb.append(customer.toString()).append("\n");
        }
        sb.append("Total: ").append(customers.size()).append(" clientes\n");

        return sb.toString();
    }
}
