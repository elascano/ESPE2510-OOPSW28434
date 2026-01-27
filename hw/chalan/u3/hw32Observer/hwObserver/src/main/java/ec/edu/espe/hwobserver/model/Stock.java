
package ec.edu.espe.hwobserver.model;
/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Stock {

    protected String symbol;
    protected double price;

    private ArrayList<IInvestor> investors = new ArrayList<>();

    public void addObserver(IInvestor investor) {
        investors.add(investor);
    }

    public void deleteObserver(IInvestor investor) {
        investors.remove(investor);
    }

    public void notifyObservers(Object args) {
        Iterator<IInvestor> iterator = investors.iterator();
        while (iterator.hasNext()) {
            iterator.next().update(this, args);
        }
    }

    public String getSymbol() {
        return symbol;
    }
}
