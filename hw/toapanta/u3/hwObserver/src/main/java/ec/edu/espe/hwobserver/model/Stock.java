//package ec.edu.espe.hwobserver.model;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//
//public abstract class Stock {
//
//    protected String symbol;
//    protected double price;
//    private ArrayList investors = new ArrayList();
//
//    public Stock(){}
//    public void addObserver(IInvestor iinvestor) {
//        investors.add(iinvestor);
//    }
//
//    public void deleteObserver(IInvestor iinvestor) {
//        investors.remove(iinvestor);
//    }
//
//    public void notifyObservers(Object args) {
//        Iterator i = investors.iterator();
//        while (i.hasNext()) {
//            IInvestor investor = (IInvestor)i.next();
//            investor.update(this, args);
//        }
//    }
//}
//
package ec.edu.espe.hwobserver.model;

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
