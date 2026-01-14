export class TaxCalculator {
    constructor(rate = 0.15) {
        this.rate = rate;
        this.factor = 1 + rate;
    }

    // Calcula el precio con IVA antes de guardarlo
    applyTax(basePrice) {
        return parseFloat((basePrice * this.factor).toFixed(2));
    }

    // Recalcula el precio base al leer de la base de datos
    recalculateBase(totalPrice) {
        return parseFloat((totalPrice / this.factor).toFixed(2));
    }

    // Calcula la sumatoria total de la lista
    sumList(products) {
        const total = products.reduce((acc, p) => acc + (p.tax_included_price || 0), 0);
        return parseFloat(total.toFixed(2));
    }
}