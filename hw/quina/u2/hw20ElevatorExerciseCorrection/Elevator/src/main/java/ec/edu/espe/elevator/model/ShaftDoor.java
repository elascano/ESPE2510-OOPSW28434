package ec.edu.espe.elevator.model;

/**
 *
 * @author Paulo Ramos
 */
public class ShaftDoor extends Door {

    public ShaftDoor(boolean isOpen) {
        super(isOpen);
    }
    
    public void Obstruction() {
        System.out.println("There is no obstruction in the door");
    }
   
}
