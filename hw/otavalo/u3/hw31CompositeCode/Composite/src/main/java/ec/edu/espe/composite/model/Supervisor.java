package ec.edu.espe.composite.model;

import java.util.Vector;

/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */
public abstract class Supervisor  extends Employee{
    protected Vector directReports = new Vector();
    
    public void add(Employee anEmployee) {
        this.directReports.addElement(anEmployee);
    }
    
    public void stateName(){
        super.stateName();
        if(directReports.size()>0)
            for (int i =0; i < directReports.size(); i++)
                ((Employee)directReports.elementAt(i)).stateName();
    }
    
}
