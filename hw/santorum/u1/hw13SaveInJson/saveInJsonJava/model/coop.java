package model;

import java.util.ArrayList;

public class coop {
    private String id;
    private ArrayList<chicken> chickens;

    public coop(String id) {
        this.id = id;
        this.chickens = new ArrayList<>();
    }

    public String getId() { return id; }
    public ArrayList<chicken> getChickens() { return chickens; }

    public void addChicken(chicken c) {
        chickens.add(c);
    }

    public chicken findChickenById(String chickenId) {
        for (chicken c : chickens) {
            if (c.getId().equals(chickenId)) {
                return c;
            }
        }
        return null;
    }

    public boolean removeChickenById(String chickenId) {
        return chickens.removeIf(c -> c.getId().equals(chickenId));
    }
}
