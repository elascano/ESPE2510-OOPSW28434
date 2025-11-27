package ec.edu.espe.elevator.model;

/**
 *
 * @author Paulo Ramos
 */
public class CageDoor extends Door {

    public CageDoor(boolean isOpen) {
        super(isOpen);
    }

    public void Obstruction() {
        System.out.println("There is no obstruction in the door");
    }

}
