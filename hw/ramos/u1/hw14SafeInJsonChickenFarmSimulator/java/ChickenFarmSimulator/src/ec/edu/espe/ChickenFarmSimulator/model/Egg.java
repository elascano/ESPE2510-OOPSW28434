/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.ChickenFarmSimulator.model;

/**
 *
 * @author Paulo Ramos
 */
public class Egg {
    // Atributo privado
    private String size;

    // Constructor
    public Egg(String size) {
        this.size = size;
    }

    // Getter
    public String getSize() {
        return size;
    }

    // Setter
    public void setSize(String size) {
        this.size = size;
    }

    // Método toString sobrescrito
    @Override
    public String toString() {
        return "Egg{size=" + size + "}";
    }
}


