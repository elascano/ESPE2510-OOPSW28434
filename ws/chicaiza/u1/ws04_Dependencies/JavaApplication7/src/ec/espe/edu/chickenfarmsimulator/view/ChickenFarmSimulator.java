/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.chickenfarmsimulator.view;
import ec.espe.edu.chickenfarmsimulator.model.Chicken;

public class ChickenFarmSimulator {
    public static void main(String[] args) {
        System.out.println("This is my Chicken Farm Simulator");

        String owner = "Daniel Chicaiza";
        String name = "Lucy";
        int id = 1;
        String color = "White and Brown";
        int age = 2;
        boolean isMolting = false;

        // Crear objeto Chicken
        Chicken chicken = new Chicken(id, name, color, age, isMolting);

        // Mostrar datos del objeto usando toString()
        System.out.println("Owner: " + owner);
        System.out.println(chicken);
        
        chicken.doStuff();
    }
}