package ec.edu.espe.eventsystem.controller;

import ec.edu.espe.eventsystem.model.Discount;
import ec.edu.espe.eventsystem.model.Event;
import utils.JsonOperations;

/**
 *
 * @author Daniel
 */
public class EventController {
    private Discount discount ;
    
    public EventController(){
        
        double percentage = JsonOperations.readDiscount();
        discount = Discount.getInstance(percentage);
    }
    
    public double getDiscount (){
        return discount.getPercentage();
    }
    
    public void updateDiscount(double newPercentage){
        
        if (newPercentage < 0 || newPercentage > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
        discount.setPercentage(newPercentage);
        JsonOperations.saveDiscount(newPercentage);
    }
    
    public double calculateTotal(Event event) {
        return event.getPrice()
             - (event.getPrice() * discount.getPercentage() / 100);
    }
}
