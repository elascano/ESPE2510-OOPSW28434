/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;
public class Colony {
    
    private Nest nest;
    private List<Ant> ants;

    public Colony() { ants = new ArrayList<>(); }

    public Colony(Nest nest) {
        this();
        this.nest = nest;
    }

    public Nest getNest() { return nest; }
    public void setNest(Nest nest) { this.nest = nest; }

    public List<Ant> getAnts() { return ants; }

    public void addAnt(Ant a) {
        if (a != null) ants.add(a);
    }

    public void removeAnt(Ant a) {
        ants.remove(a);
    }
}
