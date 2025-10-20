package ec.edu.espe.chickenfarmsimulator.view;

import ec_edu_espe_chickenfarmsimulator_model.Chicken;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
public class ChickenFarmSimulator {
    public static void main(String args []) {
        
        System.out.println("This is my Chicken Farm Simulator");
        
        Chicken chicken2 = new Chicken(0, "Maruja", "White", 1, true);
        
        String owner;
        int id = 1;
        String name = "Lucy";
        String color = "White and Brown";
        int age = 2;
        boolean isMolting = false;
        
        owner = "César Vargas";
        
        //Class object
        Chicken chicken;
        
        chicken = new Chicken(id, name, color, age, isMolting);
        
        System.out.println("The chicken is --> " + chicken);
        
        System.out.println("chicken id is --> " + chicken.getId() );
        chicken.getId();
        
        
    }
    
}
