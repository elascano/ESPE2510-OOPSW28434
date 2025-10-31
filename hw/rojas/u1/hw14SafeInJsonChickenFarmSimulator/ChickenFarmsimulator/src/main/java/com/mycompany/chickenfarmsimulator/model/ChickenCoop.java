/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chickenfarmsimulator.model;

/**
 *
 * @author JOSUE
 */
import java.util.ArrayList;
import java.util.List;

public class ChickenCoop {
    private int id;
    private List<Chicken> chickens = new ArrayList<>();

    public ChickenCoop(int id) {
        this.id = id;
    }

    public int getId() { return id; }
    public List<Chicken> getChickens() { return chickens; }

    public boolean add(Chicken chicken) {
        for (Chicken c : chickens) if (c.getId() == chicken.getId()) return false;
        chickens.add(chicken);
        return true;
    }

    public boolean remove(int chickenId) {
        for (int i = 0; i < chickens.size(); i++) {
            if (chickens.get(i).getId() == chickenId) {
                chickens.remove(i);
                return true;
            }
        }
        return false;
    }

    public Chicken find(int chickenId) {
        for (Chicken c : chickens) if (c.getId() == chickenId) return c;
        return null;
    }
}
