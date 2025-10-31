package ec.espe.edu.chickenFarmSimulator.model;
import java.util.Random;
/**
 *
 * @author Mathews Pastor
 */
public class Egg{
    private String size;
    
    public Egg() {
        String[] sizes = {"S", "M", "L"};
        Random random = new Random();
        int index = random.nextInt(sizes.length);
        this.size = sizes[index];
    }
    
    public String ToString(){
        return "Egg size => " + size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }    
    
}
