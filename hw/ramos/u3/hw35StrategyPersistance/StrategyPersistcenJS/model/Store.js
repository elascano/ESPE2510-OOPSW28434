class Store {
    constructor(id, name, price, category) {
        this.id = Number(id);
        this.name = name;
        this.price = Number(price);
        this.category = category;
    }
}

module.exports = Store;