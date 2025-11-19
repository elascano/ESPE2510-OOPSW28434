/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.doll.model;

/**
 *
 * @author LABS-ESPE
 */
public class Doll {
    private int id;
    private String name;
    private String material;
    private float price;

    public Doll(int id, String name, String material, float price) {
        this.id = id;
        this.name = name;
        this.material = material;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Doll{" + "id=" + id + ", name=" + name + ", material=" + material + ", price=" + price + '}';
    }

    
}
