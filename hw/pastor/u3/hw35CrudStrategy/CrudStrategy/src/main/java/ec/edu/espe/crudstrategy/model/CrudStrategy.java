package ec.edu.espe.crudstrategy.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
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
