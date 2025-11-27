package ec.edu.espe.elevatorsystem.model;

public class DoorCloseButton extends CageButton {
    public DoorCloseButton(int id) { super(id); }
    @Override
    public void press() {
        isPressed = true;
        System.out.println("DoorCloseButton " + id + " pressed");
    }
}

