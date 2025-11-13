package ec.edu.ec.chickenfarmsimulator.view;

import ec.edu.ec.chickenfarmsimulator.model.Chicken;
// #include chickens.h
/**
 *
 * @author Arelys Otavalo
 */
public class ChickenFarmSimulator {
    public static void main(String args[]){ //sintax for main
        
        System.out.println("This is my Chciken Farm Simulator");
        
        Chicken chicken2 = new Chicken(0, "Maruja", "White", 0, true);
        
        String owner;
        int id=1;
        String name= "Lucy";
        String color= "White and Brown";
        int age=2;
        boolean isMolting=false;
        
        owner = "Arelys Otavalo";
        
        //class object
        Chicken chicken;
        
        chicken = new Chicken(id, name, color, age, isMolting);
        
        System.out.println("the chicken is --> " + chicken);
        
        System.out.println("chicken id --> " + chicken.getId() );
        chicken.getId();
        
        chicken.doStuff();
        chicken2.doStuff();
    }
}
