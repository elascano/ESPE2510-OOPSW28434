package ec.edu.espe.storefruit.model;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
public class Fruit {
     private String name;
    private double price;
    private int stock;

    public Fruit(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
