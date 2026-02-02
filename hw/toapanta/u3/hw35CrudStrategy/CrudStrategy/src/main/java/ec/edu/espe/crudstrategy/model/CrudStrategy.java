package ec.edu.espe.crudstrategy.model;

/**
 *
 * @author Adrian Toapanta 
 */

import ec.edu.espe.crudstrategy.model.Customer;
import java.util.List;

public interface CrudStrategy {
    boolean add(Customer customer);
    boolean delete(int id);
    boolean update(int id, Customer customer);
    List<Customer> readAll();
    Customer readById(int id);
    
    String getFormatName();
}
