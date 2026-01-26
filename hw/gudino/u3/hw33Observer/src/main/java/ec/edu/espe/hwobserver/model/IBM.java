//package ec.edu.espe.hwobserver.model;
//
//import ec.edu.espe.hwobserver.view.Investor;
//
//public class IBM extends Stock {
//
//    private final String symbol;
//    private final double price;
//
//    public IBM(String symbol, double price) {
//        this.symbol = symbol;
//        this.price = price;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//        notifyObservers(price);
//    }
//
//    public String getSymbol() {
//        return symbol;
//    }
//    
//    public void setSymbol(String symbol) {
//        this.symbol = symbol;
//        notifyObservers(symbol);
//    }
//}


package ec.edu.espe.hwobserver.model;

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
