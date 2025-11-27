package ec.edu.espe.elevatorsystem.model;

public class EmergencyButton extends CageButton {
    public EmergencyButton(int id) { super(id); }
    @Override
    public void press() {
        isPressed = true;
        System.out.println("EmergencyButton " + id + " pressed");
    }
}

