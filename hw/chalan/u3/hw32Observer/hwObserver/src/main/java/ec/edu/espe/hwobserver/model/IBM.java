
package ec.edu.espe.hwobserver.model;
/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
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

    public void setSymbol(String symbol) {
        this.symbol = symbol;
        notifyObservers(symbol);
    }
}
