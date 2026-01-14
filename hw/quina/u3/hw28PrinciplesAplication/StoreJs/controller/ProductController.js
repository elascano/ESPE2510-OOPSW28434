const Product = require('../model/Product');
const ProductRepository = require('../db/ProductRepository');

class ProductController {
    constructor(uri, dbName, collectionName) {
        this.repository = new ProductRepository(uri, dbName, collectionName);
    }

    async saveProduct(id, name, price) {
        const product = new Product(id, name, price);
        const total = product.basePrice * 1.15;
        product.finalPrice = Number(total.toFixed(2));
        await this.repository.create(product);
    }

    async getInventory() {
        return await this.repository.readAll();
    }

    async searchProduct(id) {
        return await this.repository.findById(id);
    }

    async modifyProduct(id, name, price) {
        const product = new Product(id, name, price);
        product.finalPrice = Number((product.basePrice * 1.15).toFixed(2));
        await this.repository.update(product);
    }

    async removeProduct(id) {
        await this.repository.delete(id);
    }
}
module.exports = ProductController;