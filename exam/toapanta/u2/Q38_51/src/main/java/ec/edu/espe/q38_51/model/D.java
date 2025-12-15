
package ec.edu.espe.q38_51.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adrian Toapanta, Student OOP, @ESPE

 */
public class D extends A {
    
    private List<F> associatedF; 

    public D(String name) {
        super(name);

        this.associatedF = new ArrayList<>();
    }


    public void addF(F f) {
        if (associatedF.size() < 4) {
             associatedF.add(f);
        } else {
            System.out.println("Warning: D already has max F objects.");
        }
    }


    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Class D (Extends A, Aggregates F)");
        System.out.println("Aggregated F Objects: " + associatedF);
    }
}