package chickenfarmsimulator.model;

import java.util.ArrayList;
import java.util.List;

public class ChickenCoop {
    private int id;
    private List<Chicken> chickens;
    
    public ChickenCoop(int id) {
        this.id = id;
        this.chickens = new ArrayList<>();
    }
    
    public int getId() { return id; }
    public List<Chicken> getChickens() { return chickens; }
    
    public void addChicken(Chicken chicken) {
        chickens.add(chicken);
    }
    
    public void removeChicken(int chickenId) {
        chickens.removeIf(chicken -> chicken.getId() == chickenId);
    }
    
    public Chicken getChicken(int chickenId) {
        for (Chicken chicken : chickens) {
            if (chicken.getId() == chickenId) {
                return chicken;
            }
        }
        return null;
    }
}