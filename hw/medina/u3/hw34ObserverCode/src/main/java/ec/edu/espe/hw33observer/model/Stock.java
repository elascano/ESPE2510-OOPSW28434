package ec.edu.espe.hw33observer.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Joseph B. Medina
 */
public abstract class Stock {

    protected String symbol;
    protected double price;
    private ArrayList investors = new ArrayList();

    public abstract String getSymbol(); 

    public abstract double getPrice(); 

    public Stock() {
    }

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
