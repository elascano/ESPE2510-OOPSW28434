package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Josue Carvajal, THE ART OF PROGRAMMING, @ESPE
 */

public class Button {
    protected boolean isLit;

    public void press() {
        System.out.println("Botón presionado.");
        isLit = true;
    }
}
