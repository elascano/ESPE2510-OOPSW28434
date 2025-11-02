package com.espe.chickenfarm.model;

import java.util.ArrayList;
import java.util.List;

public class Farmer {
    private int id;
    private String name;
    private List<Integer> coopIds;

    public Farmer(int id, String name) {
        this.id = id;
        this.name = name;
        this.coopIds = new ArrayList<>();
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public List<Integer> getCoopIds() { return coopIds; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }

    public void addCoop(int coopId) {
        if (!coopIds.contains(coopId)) {
            coopIds.add(coopId);
        }
    }

    @Override
    public String toString() {
        return String.format("Farmer{id: %d, name: %s, coops: %d}", id, name, coopIds.size());
    }
}