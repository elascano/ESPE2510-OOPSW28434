package ec.edu.espe.calculatorustax.model;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class USTax {
    private static USTax instance;
    private float taxPercentage;
    
    private USTax(){
        this.taxPercentage = 7.5f;
    }
    
    public static USTax getInstance(){
        if(instance == null){
            instance = new USTax();
        }
        return instance;
    }
    
    public void setTaxPercentage(float tax){
        if(tax >= 0){
            this.taxPercentage = tax;
        }else{
            System.out.println("The tax percentage cannot be negative");
        }
    }
    
    public float salesTotal(float amount){
        float taxAmount = amount * (taxPercentage/100);
        return amount + taxAmount;
    }
    
    public void getSingletonData(){
        System.out.println("Tax Percentage: " + taxPercentage + "%");
    }
    
    public float getTaxPercentage(){
        return taxPercentage;
    }
    
}
