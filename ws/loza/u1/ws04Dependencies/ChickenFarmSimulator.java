/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chickenfarmsimulador.view;

import ec.edu.espe.chickenfarmsimulador.model.Chicken;

/**
 *
 * @author Steven Loza
 */
public class ChickenFarmSimulator {
    public static void main (String args[]){
        System.out.println("This is my chicken Farm Simulator");
        
        Chicken chicken2 = new Chicken(0,"maruja","white",1,true);
        String owner;
        int id = 1;
        String name ="Lucy";
        String color = "white and brown";
        int age = 2;
        boolean isMolting = false;
        
        owner = "Steven Loza";
        
        //Class object
       Chicken chicken;
       
       chicken = new Chicken(id, name, color, age, isMolting);
       
        System.out.println("the chicken is ---> "+ chicken);
        
        System.out.println("chicken Id--> " + chicken.getId() + chicken.getName());
        chicken.getId();
        
    }
    
}
