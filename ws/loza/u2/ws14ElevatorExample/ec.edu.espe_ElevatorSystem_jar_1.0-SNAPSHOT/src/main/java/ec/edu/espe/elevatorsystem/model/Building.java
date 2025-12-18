/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Steven Loza @ESPE
 */
import ec.edu.espe.elevatorsystem.view.ElevatorSystem;
import java.util.ArrayList;
import java.util.List;

public class Building {
    private String name;
    private int floors;
    private ElevatorSystem elevatorSystem;
    private List<Elevator> elevators = new ArrayList<>();

    public Building(String name, int floors) {
        this.name = name;
        this.floors = floors;
        // init elevators & system
        ElevatorA a = new ElevatorA();
        ElevatorB b = new ElevatorB();
        ElevatorC c = new ElevatorC();
        elevators.add(a);
        elevators.add(b);
        elevators.add(c);
        elevatorSystem = new ElevatorSystem(this, elevators);
    }

    public ElevatorSystem getElevatorSystem() { return elevatorSystem; }
    public List<Elevator> getElevators() { return elevators; }
    public int getFloors() { return floors; }
}

