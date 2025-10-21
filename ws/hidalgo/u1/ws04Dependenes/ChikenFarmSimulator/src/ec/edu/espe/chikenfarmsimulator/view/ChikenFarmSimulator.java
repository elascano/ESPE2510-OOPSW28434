package ec.edu.espe.chikenfarmsimulator.view;

import ec.edu.espe.chikenfarmsimulator.model.Chiken;
//#include chikens.h si fuera en C

/**
 *
 * @author Mikael Hidalgo, Dakar, @ESPE
 */
public class ChikenFarmSimulator {

    public static void main(String args[]) {

        System.out.println("This is my Chiken Farm Simulator");

        Chiken chiken2 = new Chiken(0, "Maruja", "white", 1, true);

        String owner;
        int id = 1;
        String name = "Lucy";
        String color = "black and brown";
        int age = 2;
        boolean isMolting = false;

        owner = "Mikael Hidalgo";

        //Class  object
        Chiken chiken;
        chiken = new Chiken(id, name, color, age, isMolting);
        System.out.println("The chiken is ---->" + chiken);

        System.out.println("chiken id --->" + chiken.getId() + chiken.getName());
        chiken.getId();

    }
}
