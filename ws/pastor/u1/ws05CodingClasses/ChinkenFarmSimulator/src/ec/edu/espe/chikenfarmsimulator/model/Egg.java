package ec.edu.espe.chikenfarmsimulator.model;
/**
 *
 * @author Mathews Pastor
 */
public class Egg {
    private char size;

    public Egg(char size) {
        this.size = size;
    }



    @Override
    public String toString() {
        return "Egg{" + "size=" + getSize() + '}';
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
