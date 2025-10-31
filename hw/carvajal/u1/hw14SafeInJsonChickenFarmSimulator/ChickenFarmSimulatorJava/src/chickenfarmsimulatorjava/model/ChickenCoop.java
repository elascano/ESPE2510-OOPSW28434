package chickenfarmsimulatorjava.model;

/**
 *
 * @author Gabriel
 */

import java.util.ArrayList;

public class ChickenCoop {
    private int id;
    private ArrayList<Chicken> chickens;

    public ChickenCoop(int id) {
        this.id = id;
        this.chickens = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Chicken> getChickens() {
        return chickens;
    }

    public boolean addChicken(Chicken chicken) {
        if (chickens.size() < 5) {
            chickens.add(chicken);
            return true;
        } else {
            System.out.println("Coop " + id + " is full.");
            return false;
        }
    }

    public void removeChicken(int id) {
        chickens.removeIf(c -> c.getId() == id);
    }
}
