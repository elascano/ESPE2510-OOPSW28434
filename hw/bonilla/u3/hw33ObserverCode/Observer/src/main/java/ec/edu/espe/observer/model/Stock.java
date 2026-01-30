package ec.edu.espe.observer.model;

import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */

public abstract class Stock {
    private String symbol;
    private double price;
    private ArrayList<IInvestor> investors = new ArrayList<>();

    public void addObserver(IInvestor investor) {
        investors.add(investor);
    }

    public void deleteObserver(IInvestor investor) {
        investors.remove(investor);
    }

    public void notifyObservers(Object args) {
        Iterator<IInvestor> i = investors.iterator();
        while (i.hasNext()) {
            i.next().update(this, args);
        }
    }

    public String getSymbol() {
        return symbol;
    }

    protected void setSymbolInternal(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    protected void setPriceInternal(double price) {
        this.price = price;
    }
}