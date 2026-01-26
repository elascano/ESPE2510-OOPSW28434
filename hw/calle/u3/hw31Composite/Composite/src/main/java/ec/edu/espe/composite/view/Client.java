package ec.edu.espe.composite.view;

import ec.edu.espe.composite.model.Employee;
/**
 *
 * @author Emily Calle, @ESPE
 */
public class Client {
    public static Employee employee;

    public static void doClientTasks() {
        if (employee != null) {
            employee.stateName();
        }
    }
}