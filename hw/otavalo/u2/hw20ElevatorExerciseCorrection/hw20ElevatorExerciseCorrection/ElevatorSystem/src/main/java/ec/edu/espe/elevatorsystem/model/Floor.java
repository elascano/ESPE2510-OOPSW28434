package ec.edu.espe.elevatorsystem.model;
import java.util.ArrayList;
/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class Floor {
   private int number;
    private boolean door;
    private ArrayList<Person> persons;
    private ArrayList<Button> buttons;
    private ArrayList<Button> allowedButton;

    public void checkDoors(boolean door) {
        System.out.println("Checking floor doors status.");
    }
    
    public void setPersons(ArrayList<Person> persons){ this.persons = persons; }
    public void setButtons(ArrayList<Button> buttons){ this.buttons = buttons; } 
}
