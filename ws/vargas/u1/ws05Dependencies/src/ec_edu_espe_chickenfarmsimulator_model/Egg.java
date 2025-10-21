
package ec_edu_espe_chickenfarmsimulator_model;

/**
 *
 * @author César Vargas, Paradigm,@ESPE
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
