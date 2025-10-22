package ec.edu.espe.chickenfarmsimulador.view;


/**
 *
 * @author Joseph Medina
 */
public class ChickenFarmSimulator {
    public static void main(String arg[]){
       
        System.out.println("This is my Chicen Farm Simulator");
       
        Chicken chicken2 = new Chicken(0, "Maruja", "white", 1, true);
       
        String owner;
        int id = 1;
        String name = "Lucy";
        String color = "White and Brown";
        int age = 2;
        boolean isMolting = false;
       
        owner = "Joseph Medina";
       
        //Class object
        Chicken chiken;
       
        chiken = new Chicken(id,name,color, age, isMolting);
       
        System.out.println("The chiken is --> " + chiken);
       
        System.out.println("chicken id --> \t" + chiken.getId() + "-" + chiken.getName());
        chiken.getId();
        chiken.doStuff();
    }
}