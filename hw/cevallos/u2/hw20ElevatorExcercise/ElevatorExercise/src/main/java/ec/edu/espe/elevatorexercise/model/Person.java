/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorexercise.model;

import java.util.Date;

/**
 *
 * @author Mateo Cevallos
 */
public abstract class Person {
    private float weight;
    private int id;
    private Date date;
    private int type;
    private int destinationFloor;

    public Person(float weight, int id, Date date, int type, int destinationFloor) {
        this.weight = weight;
        this.id = id;
        this.date = date;
        this.type = type;
        this.destinationFloor = destinationFloor;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }
    
    

    public void pressButton(Button button) {
        button.press();
    }

    public void enterElevator(Elevator elevator) {
        System.out.println("Entering elevator");
    }

    public void exitElevator(Elevator elevator) {
        System.out.println("Exiting elevator");
    }
}
