package model;
/**
 *
 * @author Mateo Aymacaña
 */
public class Poop {
    private int amount;
    
    @Override
    public String toString(){
        return "Poop {" + "amount = " + amount + "}";
    }
    
    public Poop(int amount){
        this.amount = amount;
    }
    
    public int getAmount(){
        return amount;
    }
  
}
