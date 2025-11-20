package ec.edu.espe.model;

/**
 *
 * @author Gabriel
 */

import java.util.ArrayList;

public class TypeCable {
    private int id;
    private ArrayList<Chicken> chickens;

    public TypeCabled) {
        this.id = id;
        this.cable = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Cable> getCable() {
        return cables;
    }

    public boolean addCable(Cable cable) {
        if (cable.size() < 5) {
            cable.add(cable);
            return true;
        } else {
            System.out.println("Cable " + id );
            return false;
        }
    }

    public void removeChicken(int id) {
        cable.removeIf(c -> c.getId() == id);
    }
}
