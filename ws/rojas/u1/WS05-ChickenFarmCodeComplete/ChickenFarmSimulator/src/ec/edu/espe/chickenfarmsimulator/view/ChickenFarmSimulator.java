package ec.edu.espe.chickenfarmsimulator.view;

import ec.edu.espe.chickenfarmsimulator.model.Chicken;

/**
 *
 * @author Josue Rojas
 * @version 0.1
 */
public class ChickenFarmSimulator {
    
    public static void main(String args[]){

        System.out.println("This is my Chicken Farm Simulator");
        
        Chicken chicken2  = new Chicken(0, "Maruja", "white", 1, true);
        
        String owner;
        int id = 1;
        String name = "Lucy";
        String color = "White and Brown";
        int age = 2;
        boolean isMolting = false;
        
        owner = "Josue Rojas";
        
        //Class object
        Chicken chicken;
        
        chicken = new Chicken(id, name, color, age, isMolting);
        
        System.out.println("The chicken is --> " + chicken);
        
        System.out.println("chicken id --> " + chicken.getId() + chicken.getName());
        chicken.getId();
        
        chicken.doStuff();
        chicken2.doStuff();
    }
}
