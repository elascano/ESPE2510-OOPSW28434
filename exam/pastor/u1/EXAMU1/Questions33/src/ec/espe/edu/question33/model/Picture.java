package ec.espe.edu.question33.model;
import java.util.ArrayList;
/**
 *
 * @author Mathews Pastor, Poower Rangers of Programing, @ESPE
 */
public class Picture {
    private String name;
    private String author;
    private float price;
    private ArrayList<String> colors;

    public Picture(String name, String author, int price) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.colors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }
}
