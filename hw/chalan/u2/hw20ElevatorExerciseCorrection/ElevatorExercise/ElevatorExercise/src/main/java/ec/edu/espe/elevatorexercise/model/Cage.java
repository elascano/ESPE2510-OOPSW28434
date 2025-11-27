/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorexercise.model;

import java.util.List;

/**
 *
 * @author Mateo Cevallos
 */
public class Cage {
    private ControlPanel controlPanel;
    private List<Person> currentPersons;

    public Cage(ControlPanel controlPanel, List<Person> currentPersons) {
        this.controlPanel = controlPanel;
        this.currentPersons = currentPersons;
    }
    

    public double getCurrentWeight() {
        System.out.println("Calculating current weight...");
        return 0.0;
    }
    
    public int getPersonCount() {
        if(currentPersons == null){
            return 0;
        }
        return currentPersons.size();
    }
    
}
