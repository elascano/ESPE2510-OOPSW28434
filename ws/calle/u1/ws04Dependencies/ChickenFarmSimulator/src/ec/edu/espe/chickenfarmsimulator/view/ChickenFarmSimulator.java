package ec.edu.espe.chickenfarmsimulator.view;
import ec.edu.espe.chickenfarmsimulator.model.Chicken;

/**
 *
 * @author Emily Calle
 */
public class ChickenFarmSimulator {
    public static void main(String arg[]){
    
    System.out.println("This is my Chicken Farm Simulator");
    
    Chicken chicken2 = new Chicken(0, "Maruja", "white", 1, true);
    
    String owner;
    int id = 1;
    String name = "Lucy";
    String color = "White and Brown";
    int age = 2;
    boolean isMolting = false;
    
    owner = "Emily Calle";
    
    //Class Object
    Chicken chicken;
    
    chicken = new Chicken(id, name, color, age, isMolting);
    
    System.out.println("the chicken is ---> " + chicken);
    
    System.out.println("chicken id --> " + chicken.getId());
    chicken.getId();
    
    
    }
    
}