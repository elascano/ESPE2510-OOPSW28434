class TaxService {
    constructor() {
        this.taxRate = 0.15; 
    }

    calculateTotal(price) {
        return price * (1 + this.taxRate);
    }
}

module.exports = TaxService;