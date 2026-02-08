class TaxCalculator {
    calculateFinalPrice(basePrice) {
        throw new Error("Not implemented");
    }
}

class SimpleTaxCalculator extends TaxCalculator {
    static TAX_RATE = 0.15;

    calculateFinalPrice(basePrice) {
        return basePrice + basePrice * SimpleTaxCalculator.TAX_RATE;
    }
}

module.exports = { TaxCalculator, SimpleTaxCalculator };
