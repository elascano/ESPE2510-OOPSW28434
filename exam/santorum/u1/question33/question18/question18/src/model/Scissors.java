
package model;

/**
 *
 * @author Thais Santórum Team 6 - Paradigm, @ESPE
 */


public class Scissors{
    private int scissorsId;
    private String brand;
    private String material;
    private String color;
    private String size;



public Scissors(int scissorsId, String brand, String material, String size, String color) {
    this.scissorsId = scissorsId;
    this.brand = brand;
    this.material = material;
    this.size = size;
    this.color = color;
}



    @Override
    public String toString() {
        return "Scissors{" + 
                "Scissors Id : " + getScissorsId() + 
                ", Brand : " + getBrand() + 
                ", Material : " + getMaterial() +
                ", Color : " + getColor() +
                ", Size : " + getSize() + '}';
        
    }

    public int getScissorsId() {
        return scissorsId;
    }

    public String getBrand() {
        return brand;
    }

    public String getMaterial() {
        return material;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public void setScissorsId(int scissorsId) {
        this.scissorsId = scissorsId;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }

 
    

}

    
   