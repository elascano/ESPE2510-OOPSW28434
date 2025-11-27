
package ec.edu.espe.elevatorexample.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class Building {

    private final int numFloors = 20;
    private String address;
    private final List<Shaft> shafts; 

    public Building(String address) {
        this.address = address;
        this.shafts = new ArrayList<>();
        System.out.println("  [Building] Building at " + address + " initialized (" + numFloors + " floors).");

        shafts.add(new Shaft(1, 60.0)); 
    }


    public int getNumFloors() {
        return numFloors;
    }

    public String getAddress() {
        return address;
    }
}