package ec.edu.espe.customers.model;

/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public class Customer {
    private String name;
    private String identification;//RUC/CI
    private String phone;
    private String email;
    private String clienType;//VIP, PREMIUM, STANDARD

    public Customer(String name, String identification, String phone, String email, String clienType) {
        this.name = name;
        this.identification = identification;
        this.phone = phone;
        this.email = email;
        this.clienType = clienType;
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
     * @return the identification
     */
    public String getIdentification() {
        return identification;
    }

    /**
     * @param identification the identification to set
     */
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * @return the clienType
     */
    public String getClienType() {
        return clienType;
    }

    /**
     * @param clienType the clienType to set
     */
    public void setClienType(String clienType) {
        this.clienType = clienType;
    }
    
    
}
