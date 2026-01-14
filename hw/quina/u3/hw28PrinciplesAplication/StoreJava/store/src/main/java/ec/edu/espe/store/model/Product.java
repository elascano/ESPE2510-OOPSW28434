package ec.edu.espe.store.model;

/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public class Product {

    private String id;
    private String name;
    private double basePrice;
    private double finalPrice;

    public Product(String id, String name, double basePrice) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
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
     * @return the basePrice
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * @param basePrice the basePrice to set
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * @return the finalPrice
     */
    public double getFinalPrice() {
        return finalPrice;
    }

    /**
     * @param finalPrice the finalPrice to set
     */
    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }
    
    

}
