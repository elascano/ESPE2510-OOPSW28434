package ec.edu.espe.observer.model;

import java.util.ArrayList;
import java.util.Iterator;
import ec.edu.espe.observer.model.IInvestor;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
public abstract class Stock {

    protected String symbol;
    protected double price;
    private ArrayList investors = new ArrayList();
    
    public abstract String getSymbol(); //add 
    public abstract double getPrice(); //
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

