/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorexercise.model;

/**
 *
 * @author Mateo Cevallos
 */
public class Brake {
    private int brakeType;
    private boolean isEngaged;

    public Brake(int brakeType, boolean isEngaged) {
        this.brakeType = brakeType;
        this.isEngaged = isEngaged;
    }
    
    
    public Brake(int brakeType) {
        this.brakeType = brakeType;
        this.isEngaged = false;
    }

    public void engage() {
        isEngaged = true;
        System.out.println("Brake engaged");
    }

    public void disengage() {
        isEngaged = false;
        System.out.println("Brake disengaged");
    } 
}
