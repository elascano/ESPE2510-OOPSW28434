package ec.edu.espe.tools.model;

import java.util.List;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
public class Tool {
    private String id;
    private String name;
    private double price;
    private List<String> materials;
    private double priceWithIva;

    public Tool(String id, String name, double price, List<String> materials, double priceWithIva) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.materials = materials;
        this.priceWithIva = priceWithIva;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }

    public double getPriceWithIva() {
        return priceWithIva;
    }

    public void setPriceWithIva(double priceWithIva) {
        this.priceWithIva = priceWithIva;
    }

    @Override
    public String toString() {
        return name + " (Mat: " + materials + ")";
    }
}
