/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.totalsales.model;

/**
 *
 * @author Bryan Gudino, ENCAPSULATED KNOWLEDGE, @ESPE
 */
public class Customer {
    private int id;
    private String name;
    private String email;
    private String type;
    private float discount;
    private float totalSale;

    public Customer() {
    }
    
    
    public Customer(int id, String name, String email, String type, float discount, float totalSale) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
        this.discount = discount;
        this.totalSale = totalSale;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the discount
     */
    public float getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(float discount) {
        this.discount = discount;
    }

    /**
     * @return the totalSale
     */
    public float getTotalSale() {
        return totalSale;
    }

    /**
     * @param totalSale the totalSale to set
     */
    public void setTotalSale(float totalSale) {
        this.totalSale = totalSale;
    }
    
    
}
