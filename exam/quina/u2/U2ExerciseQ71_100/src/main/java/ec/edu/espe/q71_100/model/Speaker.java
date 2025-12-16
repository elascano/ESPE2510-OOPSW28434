package ec.edu.espe.q71_100.model;
/**
 *
 * @author Maryuri Quina, @ESPE
 */
public class Speaker {
    private static final String CONNECTION_STRING = "mongodb+srv://maryuri:Maryuri2007@cluster0.iektq66.mongodb.net/";
    private static final String DATABASE_NAME = "SpeakerDB";
    
    private int id;
    private String name;
    private float price;
    private float power;

    @Override
    public String toString() {
        return "Speaker{" + "id=" + id + ", name=" + name + ", price=" + price + ", power=" + power + '}';
    }
    
    

    public Speaker(int id, String name, float price, float power) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.power = power;
    }
    
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the power
     */
    public float getPower() {
        return power;
    }

    /**
     * @param power the power to set
     */
    public void setPower(float power) {
        this.power = power;
    }
    
    
    
}
