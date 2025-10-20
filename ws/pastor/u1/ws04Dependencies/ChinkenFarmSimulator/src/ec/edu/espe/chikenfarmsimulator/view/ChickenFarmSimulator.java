package ec.edu.espe.chikenfarmsimulator.view;

import ec.edu.espe.chikenfarmsimulator.model.Chiken;

/**
 *
 * @author Mathews Pastor
 */
public class ChickenFarmSimulator {
    public static void main(String arg[]){
        
        System.out.println("This is my Chicen Farm Simulator");
        
        Chiken chiken2 = new Chiken(0, "Maruja", "white", 1, true);
        
        String owner;
        int id = 1;
        String name = "Lucy";
        String color = "White and Brown";
        int age = 2;
        boolean isMolting = false;
        
        owner = "Mathews Pastor";
        
        //Class object
        Chiken chiken;
        
        chiken = new Chiken(id,name,color, age, isMolting);
        
        System.out.println("The chiken is --> " + chiken);
        
        System.out.println("chicken id --> \t" + chiken.getId() + "-" + chiken.getName());
        chiken.getId();
    }
}
