package ec.edu.espe.elevatorsystem.model;

public class UpButton extends FloorButton {
    public UpButton(int id) { super(id); }
    @Override
    public void press() {
        isPressed = true;
        System.out.println("UpButton " + id + " pressed");
    }
}
