package ec.edu.espe.model;

public class Product {
    private String name;
    private double basePrice;
    private double totalPrice;

    public Product(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }
    public double getBasePrice() {
        return basePrice; 
    }
    public double getTotalPrice() {
        return totalPrice; 
    }
    public void setTotalPrice(double totalPrice) { 
        this.totalPrice = totalPrice;
    }
}