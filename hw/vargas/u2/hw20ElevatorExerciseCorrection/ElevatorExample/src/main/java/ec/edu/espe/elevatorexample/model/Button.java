
package ec.edu.espe.elevatorexample.model;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public abstract class Button {

    protected boolean isPressed = false;

    public Button() {

    }

    public void press() {
        isPressed = true;
        System.out.println("  [Button] Pressed.");
    }


    public void unpress() {
        isPressed = false;
        System.out.println("  [Button] Unpressed.");
    }


    public void reset() {
        isPressed = false;
        System.out.println("  [Button] Reset.");
    }

    public boolean isPressed() {
        return isPressed;
    } 
}
