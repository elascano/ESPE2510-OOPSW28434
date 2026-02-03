package ec.edu.espe.crudstrategy.model;

/**
 *
 * @author Adrian Toapanta 
 */

import ec.edu.espe.crudstrategy.model.Customer;
import ec.edu.espe.crudstrategy.utils.FileManager;
import java.util.ArrayList;
import java.util.List;

public class CsvStrategy implements CrudStrategy {
    private static final String FILE_PATH = "customers.csv";
    
    @Override
    public boolean add(Customer customer) {
        try {
            String line = customer.toCsv();
            FileManager.appendToFile(FILE_PATH, line);
            return true;
        } catch (Exception e) {
            System.err.println("Error adding customer to CSV: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean delete(int id) {
        try {
            List<Customer> customers = readAll();
            boolean removed = customers.removeIf(c -> c.getId() == id);
            
            if (removed) {
                StringBuilder content = new StringBuilder();
                for (Customer c : customers) {
                    content.append(c.toCsv()).append("\n");
                }
                FileManager.saveToFile(FILE_PATH, content.toString());
            }
            return removed;
        } catch (Exception e) {
            System.err.println("Error deleting from CSV: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean update(int id, Customer updatedCustomer) {
        try {
            List<Customer> customers = readAll();
            boolean updated = false;
            
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getId() == id) {
                    customers.set(i, updatedCustomer);
                    updated = true;
                    break;
                }
            }
            
            if (updated) {
                StringBuilder content = new StringBuilder();
                for (Customer c : customers) {
                    content.append(c.toCsv()).append("\n");
                }
                FileManager.saveToFile(FILE_PATH, content.toString());
            }
            return updated;
        } catch (Exception e) {
            System.err.println("Error updating in CSV: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public List<Customer> readAll() {
        List<Customer> customers = new ArrayList<>();
        try {
            String content = FileManager.readFile(FILE_PATH);
            if (content != null && !content.trim().isEmpty()) {
                String[] lines = content.split("\n");
                for (String line : lines) {
                    String[] parts = line.split(",");
                    if (parts.length == 5) {
                        Customer customer = new Customer(
                            Integer.parseInt(parts[0].trim()),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim()
                        );
                        customers.add(customer);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
        return customers;
    }
    
    @Override
    public Customer readById(int id) {
        return readAll().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public String getFormatName() {
        return "CSV";
    }
}
