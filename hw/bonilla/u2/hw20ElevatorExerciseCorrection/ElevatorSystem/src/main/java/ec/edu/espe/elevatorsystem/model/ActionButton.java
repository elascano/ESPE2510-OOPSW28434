package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class ActionButton extends Button {

    private int pressCount;

    public ActionButton() {
        this.pressCount = 0;
    }

    @Override
    public void press() {
        System.out.println("ActionButton: Action button pressed");
        pressCount++;
    }

    @Override
    public boolean isActive() {
        System.out.println("ActionButton: Checking if action is allowed");
        return true;
    }

    public int getPressCount() {
        return pressCount;
    }

    public boolean isActionAllowed() {
        System.out.println("ActionButton: Checking if action is allowed");
        return true;
    }
}
