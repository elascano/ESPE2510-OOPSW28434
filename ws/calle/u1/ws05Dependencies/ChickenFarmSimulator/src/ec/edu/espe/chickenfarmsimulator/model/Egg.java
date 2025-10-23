package ec.edu.espe.chickenfarmsimulator.model;

/**
 *
 * @author Emily Calle
 */
public class Egg {
    private char size;

    
    @Override
    public String toString() {
        return "Egg{" + "size=" + size + '}';
    }

    public Egg(char size) {
        this.size = size;
    }
    
    /**
     * @return size
     */
   
    public char getSize() {
        return size;
    }
    
    /**
     * @param size the sixe to set
     */

    public void setSize(char size) {
        this.size = size;
    }

}