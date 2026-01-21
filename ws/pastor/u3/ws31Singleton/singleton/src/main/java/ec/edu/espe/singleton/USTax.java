package ec.edu.espe.singleton;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class USTax {
    private float currentTax;
    private static USTax instance;
    
    private USTax(){
        currentTax = 0.15f;
    }

    @Override
    public String toString() {
        return "USTax{" + "currentTax=" + currentTax + '}';
    }
    
    public static USTax getInstance(){
        if(instance == null){
            instance = new USTax();
            System.out.println("The intance was create for first time");
            return instance;
        }
        System.out.println("The instance was alredy create");
        return instance;
    }
    
    public void setTaxPercentage(float tax){
        System.out.println("This method is for set a new data in the instance");
        this.currentTax = tax;
    }
    
    public float getSingletonData(){
        System.out.println("This method have the intance's data. In this case the current tax" + currentTax);
        return this.currentTax;
    }
    
    public float saleTotal(float price){
        float newPrice;
        newPrice = price + (price * this.currentTax);
        return newPrice;
    }
}
