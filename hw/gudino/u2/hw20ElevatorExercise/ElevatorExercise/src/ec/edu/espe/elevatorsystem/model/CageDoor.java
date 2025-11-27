package ec.edu.espe.elevatorsystem.model;

public class CageDoor {
    private boolean open = false;

    public void open() {
        open = true;
        System.out.println("Door opened");
    }

    public void close() {
        open = false;
        System.out.println("Door closed");
    }

    public boolean isOpen() { return open; }
}
