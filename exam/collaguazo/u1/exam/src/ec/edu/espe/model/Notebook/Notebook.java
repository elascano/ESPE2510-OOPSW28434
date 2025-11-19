package ec.edu.espe.model.Notebook;
/**
 *
 * @author Pablo Collaguazo
 */
public class Notebook {
    private String id;
    private String brand;
    private int pages;
    private String size;
    private double price;
    
    // Constructor por defecto
    public Notebook() {}
    
    // Constructor con parámetros
    public Notebook(String id, String brand, int pages, String size, double price) {
        this.id = id;
        this.brand = brand;
        this.pages = pages;
        this.size = size;
        this.price = price;
    }
    
    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    
    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }
    
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    @Override
    public String toString() {
        return String.format("Notebook [ID: %s, Brand: %s, Pages: %d, Size: %s, Price: $%.2f]", 
                           id, brand, pages, size, price);
    }
    
    // Método para convertir a JSON
    public String toJson() {
        return String.format(
            "{\"id\":\"%s\",\"brand\":\"%s\",\"pages\":%d,\"size\":\"%s\",\"price\":%.2f}",
            id, brand, pages, size, price
        );
    }
}