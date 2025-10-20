package ec.edu.espe.chickenfarmsimulator.view;

import ec.edu.espe.chickenfarmsimulator.model.Chicken;

/**
 *
 * @author Mateo Aymacaña, TAP, @ESPE
 */
public class ChickenFarmSimulator {

    public static void main(String args[]) {

        System.out.println("This is my Chicken Farm Simulator");

        String owner;
        int id = 1;
        String name = "Lucy";
        String color = "Write and Brown";
        int age = 2;
        boolean isMolting = false;

        owner = "Mateo Aymacaña";

        //Class object
        Chicken chicken;

        chicken = new Chicken(id, name, color, age, isMolting);

        System.out.println("The chicken is " + chicken);

    }
}
