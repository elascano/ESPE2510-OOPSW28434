package ec.edu.espe.product.model;

import java.util.Map;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class Product {
    private String id;
    private Map<String, Object> attributes;
    
    public Product() {
    }

    public Product(String id, Map<String, Object> attributes) {
        this.id = id;
        this.attributes = attributes;
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
     * @return the attributes
     */
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * @param attributes the attributes to set
     */
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    
}