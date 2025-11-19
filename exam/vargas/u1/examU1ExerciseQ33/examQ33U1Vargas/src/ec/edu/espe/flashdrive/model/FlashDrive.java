package ec.edu.espe.flashdrive.model;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */

public class FlashDrive {
    private int id;
    private String brand;
    private String capacity;
    private String color;

    public FlashDrive(int id, String brand, String capacity, String color) {
        this.id = id;
        this.brand = brand;
        this.capacity = capacity;
        this.color = color;
    }

    @Override
    public String toString() {
        return "FlashDrive" + "id=" + id + ", brand=" + brand + ", capacity=" + capacity + ", color=" + color ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
}
