package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class UpButton extends Button{
    public UpButton(String name) {
        super(name);
    }

    public void toRequestMoveUp() {
        System.out.println("Requesting elevator to move UP.");
    }
}
