package ec.edu.espe.observer.model;

/**
 *
 *  @author @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class IBM extends Stock {


    public IBM(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers(price);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
        notifyObservers(symbol);

    }
}
