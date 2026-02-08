package model;

public class TaxCalculator {

    //
    private static final double VAT_RATE = 0.15;

    // 
    public static double calculateVAT(double basePrice) {
        return basePrice * VAT_RATE;
    }

    //
    public static double calculatePriceWithVAT(double basePrice) {
        return basePrice + calculateVAT(basePrice);
    }

    //
    private TaxCalculator() {
    }
}

