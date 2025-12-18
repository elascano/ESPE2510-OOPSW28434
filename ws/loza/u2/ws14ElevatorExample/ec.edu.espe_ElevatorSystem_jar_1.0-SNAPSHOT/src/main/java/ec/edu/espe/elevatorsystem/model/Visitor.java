/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
public class Visitor extends Person {
    public Visitor(String name, double weight) {
        super(name, weight);
    }

    @Override
    public String getType() { return "Visitor"; }
}
