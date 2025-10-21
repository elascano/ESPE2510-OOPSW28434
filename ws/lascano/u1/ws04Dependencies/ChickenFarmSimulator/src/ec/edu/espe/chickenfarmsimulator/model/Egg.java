package ec.edu.espe.chickenfarmsimulator.model;

/**
 *
 * @author Edison Lascano, Object Masters, @ESPE
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
     * @return the size
     */
    public char getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(char size) {
        this.size = size;
    }
    
    
    
    
}
