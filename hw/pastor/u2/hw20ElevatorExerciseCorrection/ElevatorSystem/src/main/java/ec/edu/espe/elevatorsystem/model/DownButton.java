package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class DownButton extends Button{
    public DownButton(String name) {
        super(name);
    }

    public void toRequestMoveDown() {
        System.out.println("Requesting elevator to move DOWN.");
    }
}
