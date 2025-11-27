package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class AlarmActivationButton extends Button{
    public AlarmActivationButton(String name) {
        super(name);
    }

    public void activateAlarm() {
        System.out.println("Alarm Activated!");
    }
}
