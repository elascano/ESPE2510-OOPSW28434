package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Josue Carvajal, THE ART OF PROGRAMMING, @ESPE
 */
public class Cabin {
    private boolean light;

    public void turnLightOn() {
        light = true;
        System.out.println("Luz de la cabina encendida.");
    }

    public void turnLightOff() {
        light = false;
        System.out.println("Luz de la cabina apagada.");
    }
}
