class Product {
    constructor(id, name, basePrice) {
        this.id = id;
        this.name = name;
        this.basePrice = parseFloat(basePrice);
        this.finalPrice = 0;
    }
}
module.exports = Product;