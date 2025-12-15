
package ec.edu.espe.q38_51.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adrian Toapanta, Student OOP, @ESPE

 */
public class C extends A {

    private List<E> partsE;

    public C(String name) {
        super(name);
        // Initialization for Composition
        this.partsE = new ArrayList<>();
        partsE.add(new E("Core"));
        partsE.add(new E("Side"));
    }

    // Overrides the base method
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Class C (Extends A, Composes E)");
        System.out.println("Composed E Objects: " + partsE);
    }
}