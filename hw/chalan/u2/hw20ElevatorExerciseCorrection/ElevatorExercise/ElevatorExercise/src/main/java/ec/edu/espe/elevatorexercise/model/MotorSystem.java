/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorexercise.model;

/**
 *
 * @author Mateo Cevallos
 */
public class MotorSystem {
    private int cables;
    private double currentSpeed;
    private double maxSpeed;

    public MotorSystem(int cables, double maxSpeed) {
        this.cables = cables;
        this.maxSpeed = maxSpeed;
    }

    public void accelerate() {
        System.out.println("Motor accelerating...");
    }

    public void decelerate() {
        System.out.println("Motor decelerating...");
    }

    public void maintainSpeed() {
        System.out.println("Maintaining speed...");
    } 
}
