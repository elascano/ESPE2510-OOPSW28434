export default class Product {
    constructor(name, stock) {
        this.name = name;
        this.stock = stock;
    }

    sell(quantity) {
        if (quantity <= this.stock) {
            this.stock -= quantity;
        }
    }

    restock(quantity) {
        this.stock += quantity;
    }
}
