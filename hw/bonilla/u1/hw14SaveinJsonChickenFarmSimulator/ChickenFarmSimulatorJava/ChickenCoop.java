package ChickenFarmSimulatorJava;

import java.util.ArrayList;
import java.util.List;

public class ChickenCoop {
    private static int idCounter = 1;

    private int id;
    private String name;
    private List<Chicken> chickens;

    public ChickenCoop(String name) {
        this.id = idCounter++;
        this.name = name;
        this.chickens = new ArrayList<>();
    }

    public void addChicken(Chicken chicken) {
        chickens.add(chicken);
    }

    public List<Chicken> getChickens() {
        return chickens;
    }

    public int getNextChickenId() {
        return chickens.size() + 1;
    }

    @Override
    public String toString() {
        return "ChickenCoop { " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfChickens=" + chickens.size() +
                " }";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}