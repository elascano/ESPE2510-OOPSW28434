/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public abstract class Person {
    protected String name;
    protected double weight;

    public Person(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() { return name; }
    public double getWeight() { return weight; }
    public abstract String getType();

    @Override
    public String toString() {
        return "Name: " + name + " | Weight: " + weight + "kg | Type: " + getType();
    }
}
