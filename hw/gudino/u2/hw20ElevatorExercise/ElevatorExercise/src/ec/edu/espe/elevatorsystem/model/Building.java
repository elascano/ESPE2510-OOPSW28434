
package ec.edu.espe.elevatorsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private String name;
    private List<Floor> floors;
    private List<Elevator> elevators;

    public Building(String name) {
        this.name = name;
        this.floors = new ArrayList<>();
        this.elevators = new ArrayList<>();
    }

    public void addFloor(Floor f) { floors.add(f); }
    public void addElevator(Elevator e) { elevators.add(e); }

    // getters
    public String getName() { return name; }
    public List<Floor> getFloors() { return floors; }
    public List<Elevator> getElevators() { return elevators; }
}

