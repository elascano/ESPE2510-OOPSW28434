/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.ChickenFarmSimulator.model;

/**
 *
 * @author Paulo Ramos
 */
import java.util.ArrayList;
import java.util.List;

public class ChickenFarmer {
    private String name;
    private List<ChickenCoop> coops;

    // Constructor
    public ChickenFarmer(String name) {
        this.name = name;
        this.coops = new ArrayList<>();
    }

    // Agregar un gallinero
    public void addCoop(ChickenCoop coop) {
        coops.add(coop);
    }

    // Obtener todos los pollos de todos los gallineros
    public List<Chicken> getAllChickens() {
        List<Chicken> allChickens = new ArrayList<>();
        for (ChickenCoop coop : coops) {
            allChickens.addAll(coop.getChickens());
        }
        return allChickens;
    }

    // Getter y Setter para el nombre
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}


