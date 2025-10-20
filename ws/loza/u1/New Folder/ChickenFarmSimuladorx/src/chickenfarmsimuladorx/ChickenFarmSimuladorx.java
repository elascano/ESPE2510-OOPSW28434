/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chickenfarmsimuladorx;

/**
 *
 * @author Loza Steven Sebastian
 *
 */
public class ChickenFarmSimuladorx {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int numberOfWheels;
        float cost;
        String owner;

        System.out.println("Hello World of OOP Programmers");
        System.out.println("From Steven Loza");

        //TODO The user must enter the values
        numberOfWheels = 5;
        cost = 100.25F;
        owner = "Steven Loza";

        System.out.println("the number of wheels of a car is ---->" + numberOfWheels
                + "and their cost is ---->$"
                + cost + "and  they belog to " + owner);

        if (numberOfWheels <= 5 && numberOfWheels >= 4) {
            System.out.println("this is a vehicule");
        } else {
            System.out.println("it is a mootorcycle");
        }

    }
}


