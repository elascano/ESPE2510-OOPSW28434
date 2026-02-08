package model;

import org.bson.types.ObjectId;  // 🔥 nuevo

public class Product {

    private ObjectId id; // 🔥 CLAVE ÚNICA MONGO

    private String name;
    private double basePrice;
    private double priceWithVAT;
    private int stock;
    private String status;
    private double vatAccumulated;

    // Constructor
    public Product(String name, double basePrice, double priceWithVAT, int stock) {
        this.id = null; // inicial nulo, MongoDB lo asigna
        this.name = name;
        this.basePrice = basePrice;
        this.priceWithVAT = priceWithVAT;
        this.stock = stock;
        this.status = calculateStatus(stock);
        this.vatAccumulated = 0;
    }

    // 🔥 GETTER / SETTER para id
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }

    // Resto de getters y setters como los tenías
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
        this.priceWithVAT = basePrice * 1.15;
    }

    public double getPriceWithVAT() { return priceWithVAT; }
    public void setPriceWithVAT(double priceWithVAT) { this.priceWithVAT = priceWithVAT; }

    public int getStock() { return stock; }
    public void setStock(int stock) {
        this.stock = stock;
        this.status = calculateStatus(stock);
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getVatAccumulated() { return vatAccumulated; }
    public void addVatAccumulated(double vat) { this.vatAccumulated += vat; }

    private String calculateStatus(int stock) {
        return stock > 0 ? "AVAILABLE" : "OUT_OF_STOCK";
    }
}
