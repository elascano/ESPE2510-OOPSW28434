package ec.edu.espe.practica.model;

/**
 *
 * @author JOSUE
 */
public class Product {

    private String id;
    private String description;
    private double taxIncludedPrice;

    public Product(String id, String description, double price) {
        this.id = id;
        this.description = description;
        this.taxIncludedPrice = price;
    }
    public String getId() { return id; }
    public String getDescription() { return description; }
    public double getTaxIncludedPrice() { return taxIncludedPrice; }

}
