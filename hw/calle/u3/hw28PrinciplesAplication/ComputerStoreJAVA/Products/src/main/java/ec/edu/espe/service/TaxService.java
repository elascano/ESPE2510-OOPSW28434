package ec.edu.espe.service;

public class TaxService {
    private static final double TAX_RATE = 0.15;

    public double calculateFinalPrice(double basePrice) {
        return basePrice + (basePrice * TAX_RATE);
    }
}