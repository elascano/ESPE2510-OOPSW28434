
package ec.edu.espe.elevatorsystem.model;

public class DoorOpenButton extends CageButton {
    public DoorOpenButton(int id) { super(id); }
    @Override
    public void press() {
        isPressed = true;
        System.out.println("DoorOpenButton " + id + " pressed");
    }
}
