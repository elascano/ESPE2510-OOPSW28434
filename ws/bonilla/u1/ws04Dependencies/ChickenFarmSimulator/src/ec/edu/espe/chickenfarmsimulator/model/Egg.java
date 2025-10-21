package ec.edu.espe.chickenfarmsimulator.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
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

    public char getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }
     
}
