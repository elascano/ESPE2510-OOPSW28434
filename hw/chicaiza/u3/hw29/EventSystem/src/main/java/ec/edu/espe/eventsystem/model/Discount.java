package ec.edu.espe.eventsystem.model;

/**
 *
 * @author Daniel
 */
public class Discount {
    
    private static Discount discount;
    
    private double percentage;
    
    private Discount (double percentage){
        
        this.percentage= percentage;
        
    }
    
    public static Discount getInstance(double percentage){
        if (discount == null){
            discount = new Discount(percentage);
            
        }
        return discount;
    }
    
    public void setPercentage (double percentage){
        this.percentage=percentage;
    }
    
    public double getPercentage(){
        return percentage;
    }
}
