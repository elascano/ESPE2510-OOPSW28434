
package ec.edu.espe.q38_51.model;

/**
 *
 * @author Adrian Toapanta, Student OOP, @ESPE

 */

public class A{
    private String name;

    public A(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void displayInfo() {
        System.out.println("--- Class A (Base) ---");
        System.out.println("Object Name: " + this.name);
    }
}