export default class Inventory {
    constructor() {
        this.products = [];
    }

    getProducts() {
        return this.products;
    }

    addProduct(product) {
        this.products.push(product);
    }

    sellProduct(index, quantity) {
        this.products[index].sell(quantity);
    }

    restockProduct(index, quantity) {
        this.products[index].restock(quantity);
    }
}
