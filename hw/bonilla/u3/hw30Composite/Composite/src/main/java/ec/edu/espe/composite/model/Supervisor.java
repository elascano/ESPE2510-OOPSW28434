package ec.edu.espe.composite.model;

import java.util.Vector;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public abstract class Supervisor extends Employee {

    private Vector<Employee> directReports = new Vector<>();

    public void add(Employee anEmployee) {
        directReports.add(anEmployee);
    }

    @Override
    public void stateName() {
        super.stateName();
        for (Employee e : directReports) {
            e.stateName();
        }
    }
}