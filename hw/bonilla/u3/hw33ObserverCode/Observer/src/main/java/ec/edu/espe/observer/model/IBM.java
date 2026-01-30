package ec.edu.espe.observer.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class IBM extends Stock {

    public IBM(String symbol, double price) {
        setSymbolInternal(symbol);
        setPriceInternal(price);
    }

    public void setPrice(double price) {
        setPriceInternal(price);
        notifyObservers(price);
    }

    public void setSymbol(String symbol) {
        setSymbolInternal(symbol);
        notifyObservers(symbol);
    }
}