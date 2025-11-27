package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public abstract class Door {
    private boolean isOpen;

    public void openAutomatically() {
        System.out.println("Door is opening automatically.");
        this.isOpen = true;
    }

    public void closeAutomatically() {
        System.out.println("Door is closing automatically.");
        this.isOpen = false;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
}
