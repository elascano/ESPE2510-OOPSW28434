package model;

import java.util.ArrayList;
import java.util.Iterator;

public class ChickenCoop {
    private int id;
    private String description;
    private ArrayList<Chicken> chickens;
    
    public ChickenCoop(int id, String description) {
        this.id = id;
        this.description = description;
        this.chickens = new ArrayList<>();
    }
    
    public void addChicken(Chicken chicken) {
        chickens.add(chicken);
        System.out.println("Chicken " + chicken.getName() + " added to coop " + id);
    }
    
    public void removeChicken(int chickenId) {
        Iterator<Chicken> iterator = chickens.iterator();
        boolean found = false;
        
        while (iterator.hasNext()) {
            Chicken chicken = iterator.next();
            if (chicken.getId() == chickenId) {
                iterator.remove();
                System.out.println("Chicken with ID " + chickenId + " removed from coop " + id);
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Chicken with ID " + chickenId + " not found in coop " + id);
        }
    }
    
    public void listChickens() {
        System.out.println("\n--- Chickens in Coop " + id + " - " + description + " ---");
        if (chickens.isEmpty()) {
            System.out.println("No chickens in this coop.");
        } else {
            for (Chicken chicken : chickens) {
                System.out.println("  " + chicken);
            }
        }
    }
    
    public void makeAllDoStuff() {
        System.out.println("\n--- All chickens in Coop " + id + " are active! ---");
        if (chickens.isEmpty()) {
            System.out.println("No chickens in this coop to do stuff.");
            return;
        }
        
        for (Chicken chicken : chickens) {
            System.out.println("\n--- " + chicken.getName() + " is doing stuff ---");
            chicken.doStuff();
        }
    }
    
    public Chicken findChickenById(int chickenId) {
        for (Chicken chicken : chickens) {
            if (chicken.getId() == chickenId) {
                return chicken;
            }
        }
        return null;
    }
    
    
    public ArrayList<Chicken> getChickens() {
        return this.chickens;
    }
    
    public int getId() {
        return id; 
    }
    
    public String getDescription() {
        return description; 
    }
    
    public int getChickenCount() {
        return chickens.size(); 
    }
    
    @Override
    public String toString() {
        return String.format("ChickenCoop{id=%d, description='%s', chickens=%d}", 
                           id, description, chickens.size());
    }
}