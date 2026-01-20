package ec.edu.espe.alarmservice.model;

/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public class Product {

    private int id;
    private String name;
    private int stock;

    public Product() {
    }

    public Product(int id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
