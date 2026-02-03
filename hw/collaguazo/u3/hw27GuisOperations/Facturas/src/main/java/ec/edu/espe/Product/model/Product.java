/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.Product.model;

public class Product {

    private int id;            
    private String name;
    private int quantity;
    private double price;
    private double subtotal;
    private double iva;
    private double total;

    
    public Product() {
    }


    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        calculateValues();
    }

    
    public void calculateValues() {
        this.subtotal = price * quantity;
        this.iva = subtotal * 0.12;
        this.total = subtotal + iva;
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
        calculateValues();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateValues();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        calculateValues();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getIva() {
        return iva;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               " | " + name +
               " | Qty: " + quantity +
               " | Total: $" + String.format("%.2f", total);
    }
}

