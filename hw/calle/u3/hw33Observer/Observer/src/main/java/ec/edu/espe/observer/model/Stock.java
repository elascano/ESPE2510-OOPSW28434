package ec.edu.espe.observer.model;

/**
 *
 * @author Emily Calle, @ESPE
 */
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Stock {
    protected String symbol;
    protected double price;
    private ArrayList<IInvestor> investors = new ArrayList<>();
    
    public abstract String getSymbol();
    public abstract double getPrice();

    public void addObserver(IInvestor iinvestor) { 
        investors.add(iinvestor); 
    }
    public void deleteObserver(IInvestor iinvestor) {
        investors.remove(iinvestor);
    }

    public void notifyObservers(Object args) {
        Iterator i = investors.iterator();
        while (i.hasNext()) {
            IInvestor investor = (IInvestor) i.next();
            investor.update(this, args);
        }
    }
}