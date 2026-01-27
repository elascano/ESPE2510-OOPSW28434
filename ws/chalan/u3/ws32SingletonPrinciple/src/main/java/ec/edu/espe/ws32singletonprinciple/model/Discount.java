
package ec.edu.espe.ws32singletonprinciple.model;


public class Discount {
    private double percentage;

    public Discount(double percentage) {
        this.percentage = percentage;
    }

    /**
     * @return the percentage
     */
    public double getPercentage() {
        return percentage;
    }

    /**
     * @param percentage the percentage to set
     */
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    
    
}
