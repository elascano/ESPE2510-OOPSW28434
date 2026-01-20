package ec.edu.espe.event.model;

/**
 *
 * @author Pablo
 */
public class Event {
    private String nameEvent;
    private double price;

    public Event() {
    }
    
    
    public Event(String nameEvent, double price) {
        this.nameEvent = nameEvent;
        this.price = price;
    }
    
    /**
     * @return the nameEvent
     */
    public String getNameEvent() {
        return nameEvent;
    }

    /**
     * @param nameEvent the nameEvent to set
     */
    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    
}