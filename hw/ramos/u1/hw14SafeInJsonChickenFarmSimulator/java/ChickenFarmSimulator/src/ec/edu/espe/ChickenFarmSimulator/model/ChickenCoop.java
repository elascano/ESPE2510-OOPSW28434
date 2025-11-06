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

public class ChickenCoop {
    private int id;
    private List<Chicken> chickens;

    // Constructor
    public ChickenCoop(int id) {
        this.id = id;
        this.chickens = new ArrayList<>();
    }

    // Agregar un pollo al gallinero
    public void addChicken(Chicken chicken) {
        chickens.add(chicken);
    }

    // Listar los pollos en consola
    public void listChickens() {
        if (chickens.isEmpty()) {
            System.out.println("ChickenCoop " + id + " is empty.");
        } else {
            System.out.println("ChickenCoop " + id + " contains:");
            for (Chicken chicken : chickens) {
                System.out.println("  " + chicken.toString());
            }
        }
    }

    // Método toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChickenCoop(id=").append(id).append(")\n");
        for (Chicken chicken : chickens) {
            sb.append("  ").append(chicken.toString()).append("\n");
        }
        return sb.toString();
    }

    // Getter para los pollos
    public List<Chicken> getChickens() {
        return chickens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}


