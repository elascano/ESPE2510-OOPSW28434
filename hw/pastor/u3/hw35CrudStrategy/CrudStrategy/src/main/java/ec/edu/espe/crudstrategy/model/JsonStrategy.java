package ec.edu.espe.crudstrategy.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
import ec.edu.espe.crudstrategy.model.Customer;
import ec.edu.espe.crudstrategy.utils.FileManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonStrategy implements CrudStrategy {

    private static final String FILE_PATH = "customers.json";

    @Override
    public boolean add(Customer customer) {
        try {
            List<Customer> customers = readAll();
            customers.add(customer);

            JSONArray jsonArray = new JSONArray();
            for (Customer c : customers) {
                JSONObject obj = new JSONObject();
                obj.put("id", c.getId());
                obj.put("name", c.getName());
                obj.put("email", c.getEmail());
                obj.put("phone", c.getPhone());
                jsonArray.put(obj);
            }

            FileManager.saveToFile(FILE_PATH, jsonArray.toString(4));
            return true;
        } catch (Exception e) {
            System.err.println("Error adding customer to JSON: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            List<Customer> customers = readAll();
            boolean removed = customers.removeIf(c -> c.getId() == id);

            if (removed) {
                JSONArray jsonArray = new JSONArray();
                for (Customer c : customers) {
                    JSONObject obj = new JSONObject();
                    obj.put("id", c.getId());
                    obj.put("name", c.getName());
                    obj.put("email", c.getEmail());
                    obj.put("phone", c.getPhone());
                    jsonArray.put(obj);
                }

                FileManager.saveToFile(FILE_PATH, jsonArray.toString(4));
            }
            return removed;
        } catch (Exception e) {
            System.err.println("Error deleting from JSON: " + e.getMessage());
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
                JSONArray jsonArray = new JSONArray();
                for (Customer c : customers) {
                    JSONObject obj = new JSONObject();
                    obj.put("id", c.getId());
                    obj.put("name", c.getName());
                    obj.put("email", c.getEmail());
                    obj.put("phone", c.getPhone());
                    jsonArray.put(obj);
                }

                FileManager.saveToFile(FILE_PATH, jsonArray.toString(4));
            }
            return updated;
        } catch (Exception e) {
            System.err.println("Error updating in JSON: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Customer> readAll() {
        List<Customer> customers = new ArrayList<>();
        try {
            String content = FileManager.readFile(FILE_PATH);
            if (content != null && !content.trim().isEmpty()) {
                JSONArray jsonArray = new JSONArray(content);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    Customer customer = new Customer(
                            obj.getInt("id"),
                            obj.getString("name"),
                            obj.getString("email"),
                            obj.getString("phone")
                    );
                    customers.add(customer);
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading JSON: " + e.getMessage());
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
        return "JSON";
    }
}
