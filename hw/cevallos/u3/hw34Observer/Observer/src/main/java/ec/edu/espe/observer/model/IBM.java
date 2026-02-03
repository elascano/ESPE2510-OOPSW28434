/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.observer.model;

/**
 *
 * @author Mateo Cevallos
 */
public class IBM extends Stock {

    public IBM(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        double oldPrice = this.price;
        this.price = price;
        if (oldPrice != price) {
            notifyObservers(price);
        }
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        String oldSymbol = this.symbol;
        this.symbol = symbol;
        if (!oldSymbol.equals(symbol)) {
            notifyObservers(symbol);
        }
    }
}
