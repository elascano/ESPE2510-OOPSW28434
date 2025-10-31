package com.espe.chickenfarm.model;

import java.util.ArrayList;
import java.util.List;

public class ChickenCoop {
    private int id;
    private int farmerId;
    private List<Chicken> chickens;

    public ChickenCoop(int id, int farmerId) {
        this.id = id;
        this.farmerId = farmerId;
        this.chickens = new ArrayList<>();
    }

    // Getters
    public int getId() { return id; }
    public int getFarmerId() { return farmerId; }
    public List<Chicken> getChickens() { return chickens; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setFarmerId(int farmerId) { this.farmerId = farmerId; }

    public void addChicken(Chicken chicken) {
        chickens.add(chicken);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ChickenCoop{id: %d, farmerId: %d}%n", id, farmerId));
        for (Chicken chicken : chickens) {
            sb.append("  ").append(chicken.toString()).append("\n");
        }
        return sb.toString();
    }
}