class Product {
    constructor(name, make, basePrice, finalPrice) {
        this.name = name;
        this.make = make;
        this.basePrice = basePrice;
        this.finalPrice = finalPrice;
    }

    toDocument() {
        return {
            name: this.name,
            make: this.make,
            base_price: this.basePrice,
            final_price: this.finalPrice
        };
    }
}

module.exports = Product;
