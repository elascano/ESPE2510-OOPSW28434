
package ec.edu.espe.elevatorexample.model;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public abstract class Door {
    private boolean isOpen = false;
 
    public boolean isOpen(){
        return isOpen;
    }

    public void open() {
        if (!isOpen) {
            isOpen = true;
            System.out.println("  [Door] Opening...");
        }
    }

    public void close() {
        if (isOpen) {
            isOpen = false;
            System.out.println("  [Door] Closing...");
        }
    }
}
