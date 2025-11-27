package ec.edu.espe.elevator.model;

/**
 *
 * @author Paulo Ramos
 */
public abstract class Cage {
    
    private int maxPerson;

    public Cage(int maxPerson) {
        this.maxPerson = maxPerson;
    }

    
    /**
     * @return the maxPerson
     */
    public int getMaxPerson() {
        return maxPerson;
    }

    /**
     * @param maxPerson the maxPerson to set
     */
    public void setMaxPerson(int maxPerson) {
        this.maxPerson = maxPerson;
    }
    
    
    
}
