
package ec.edu.espe.q38_51.model;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adrian Toapanta, Student OOP, @ESPE

 */

public class B extends A {
    private List<H> listOfH; 

    public B(String name) {
        super(name);
        // Initialization for Association
        this.listOfH = new ArrayList<>();
    }

    public void addH(H h) {
        listOfH.add(h);
    }

 
    @Override 
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Class B (Extends A, Associates H)");
        System.out.println("Associated H Objects (count: " + listOfH.size() + "):");
        for (H h : listOfH) {
            System.out.println("  - " + h.getDescription());
        }
    }
}





