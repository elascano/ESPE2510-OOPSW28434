package ec.edu.espe.CellPhone.model;

/**
 *
 * @author Emily Calle
 */
public class CellPhone {
    private int name;
    private String brand;
    private double price;

    public CellPhone(int id, String brand, double price) {
        this.name = id;
        this.brand = brand;
        this.price = price;
    }

    public int getId() {
        return name;
    }

    public void setId(int id) {
        this.name = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID: " + name + ", Brand: " + brand + ", Price: $" + price;
    }
}

