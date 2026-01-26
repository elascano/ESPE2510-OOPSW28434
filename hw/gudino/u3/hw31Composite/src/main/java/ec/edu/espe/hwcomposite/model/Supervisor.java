package ec.edu.espe.hwcomposite.model;

import java.util.Vector;

public abstract class Supervisor extends Employee {
     protected Vector<Employee> directReports = new Vector<Employee>();

    @Override
    public void stateName() {
        super.stateName(); 
        
        if (directReports.size() > 0) { 
            for (int i = 0; i < directReports.size(); ++i) {

                ((Employee)directReports.elementAt(i)).stateName();
            }
        }
    }

    public void add(Employee anEmployee) {
        this.directReports.addElement(anEmployee);
    }
}