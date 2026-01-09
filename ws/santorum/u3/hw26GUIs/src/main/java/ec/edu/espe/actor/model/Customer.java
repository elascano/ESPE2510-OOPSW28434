package ec.edu.espe.actor.model;

public class Customer {

    private int id;
    private String fullName;
    private String email;
    private String type;
    private int discount;
    private int totalSale;

    public Customer() {
    }

    public Customer(int id, String fullName, String email, String type, int discount, int totalSale) {
        this.id = id;
        this.fullName = fullName;
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
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
    public int getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * @return the totalSale
     */
    public int getTotalSale() {
        return totalSale;
    }

    /**
     * @param totalSale the totalSale to set
     */
    public void setTotalSale(int totalSale) {
        this.totalSale = totalSale;
    }



    
    
    

}
