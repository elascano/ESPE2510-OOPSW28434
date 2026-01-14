package ec.edu.espe.contacts.model;

import java.util.Objects;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
public class Contact {

    private int id;
    private String fullName;
    private String email;
    private ContactType type;
    private int discount;
    private double totalSale;

    public Contact() {
    }

    public Contact(int id, String fullName, String email, ContactType type, int discount, double totalSale) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.type = type;
        this.discount = discount;
        this.totalSale = totalSale;
    }

    // Método adicional para aceptar int en totalSale también
    public Contact(int id, String fullName, String email, ContactType type, int discount, int totalSale) {
        this(id, fullName, email, type, discount, (double) totalSale);
    }

    public double calculateFinalPrice() {
        double discountAmount = totalSale * (discount / 100.0);
        return totalSale - discountAmount;
    }

    public double getSavings() {
        return totalSale - calculateFinalPrice();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(double totalSale) {
        this.totalSale = totalSale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return id == contact.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Contact{"
                + "id=" + id
                + ", fullName='" + fullName + '\''
                + ", email='" + email + '\''
                + ", type=" + type
                + ", discount=" + discount
                + ", totalSale=" + totalSale
                + '}';
    }
}
