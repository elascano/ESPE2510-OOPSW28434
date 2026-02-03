package ec.edu.espe.ws32singletonprinciple.model;

/**
 *
 * @author Kevin chalan, OOP, ESPE
 */
public class DiscountSession {

    private static DiscountSession instance;
    private Discount currentDiscount;

    private DiscountSession() {
        currentDiscount = new Discount(0);
    }

    public static DiscountSession getInstance() {
        if (instance == null) {
            instance = new DiscountSession();
        }
        return instance;
    }

    public Discount getCurrentDiscount() {
        return currentDiscount;
    }

    public void setCurrentDiscount(Discount discount) {
        this.currentDiscount = discount;
    }
}
