package ec.edu.espe.elevatorsystem.model;

public class DownButton extends FloorButton {
    public DownButton(int id) { super(id); }
    @Override
    public void press() {
        isPressed = true;
        System.out.println("DownButton " + id + " pressed");
    }
}

