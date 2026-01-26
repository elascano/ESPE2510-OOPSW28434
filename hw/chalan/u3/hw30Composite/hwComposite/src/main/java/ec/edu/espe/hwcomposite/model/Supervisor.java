package ec.edu.espe.hwcomposite.model;

import java.util.Vector;

public abstract class Supervisor extends Employee {
     protected Vector<Employee> directReports = new Vector<Employee>(); // Children

    @Override
    public void stateName() {
        super.stateName(); // Composite operation
        
        for (Employee e : directReports) {
            e.stateName(); // Recursive call
        }
    }

    public void add(Employee anEmployee) {
        this.directReports.add(anEmployee); // Add child
    }
}
