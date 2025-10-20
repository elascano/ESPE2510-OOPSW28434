package ec.edu.espe.chickenfarmsimulator.view;

import ec.edu.espe.chickenfarmsimulator.model.Chicken;

/**
 *
 * @author Paulo Ramos, @ESPE
 */
public class ChickenFarmSimulator {

    public static void main (String []args){
        
        System.out.println("This is my Chicken Farm Simulator");
        
        Chicken chicken2 = new Chicken (0, "Maruja", "White",1, true);
        
        String owner;
        int id = 1;
        String name ="Lucy";
        String color = "white and Brown";
        int age = 2;
        boolean isMolting = false;
               
        owner = "Paulo Ramos";
        
        //Class Object
        Chicken chicken;

        chicken = new Chicken(id, name, color, age, isMolting);
        
        System.out.println("the chicken is --> " + chicken);
        
        System.out.println("chicken id --> " + chicken.getId());
        chicken.getId();
    }
}
