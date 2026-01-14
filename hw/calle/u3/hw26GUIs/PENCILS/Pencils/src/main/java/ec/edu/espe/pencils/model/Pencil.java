package ec.edu.espe.pencils.model;

public class Pencil {
    private String id;
    private String brand;
    private String color;
    private double price;

    public Pencil(String id, String brand, String color, double price) {
        this.id = id;
        this.brand = brand;
        this.color = color;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

   
}