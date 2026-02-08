const Product = require("../models/Product");

class ProductController {
    constructor(taxCalculator, repository) {
        this.taxCalculator = taxCalculator;
        this.repository = repository;
    }

    async addProduct(name, make, basePrice) {
        const finalPrice =
            this.taxCalculator.calculateFinalPrice(basePrice);

        const product = new Product(
            name,
            make,
            basePrice,
            finalPrice
        );

        await this.repository.save(product);
        return product;
    }

    async getAllProducts() {
        return await this.repository.getAll();
    }

    async getTotalSum() {
        return await this.repository.getTotalSum();
    }
}

module.exports = ProductController;
