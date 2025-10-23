
package ec_edu_espe_chickenfarmsimulator.view;

import ec_edu_espe_chickenfarmsimulator.model.Chicken;
// #include chicken.h
/**
 *
 * @author Paulo Ramos
 */
public class ChickenFarmSimulator {
    
    public static void main(String args[]){
        
        System.out.println("This is my Chicken Farm Simulator. ");
        
        Chicken chicken2  = new Chicken(0, "Maruja", "white", 1, true);
        
        String owner;
        int id = 1;
        String name = "Lucy";
        String color = "White and brown.";
        int age = 2;
        boolean isMolting = false;
        
        
                
        owner = "Paulo Ramos";
        
        Chicken chicken;
        
        chicken = new Chicken(id, name, color, age, isMolting);
        
        System.out.println("The chicken is " + chicken);
        
        System.out.println("\nChicken id : " + chicken.getId() + chicken.getName());
        chicken.getId();

        chicken.doStuff();
        chicken2.doStuff();
    }
    
}
