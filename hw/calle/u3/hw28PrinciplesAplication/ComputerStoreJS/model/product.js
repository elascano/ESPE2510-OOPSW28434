class Product {
    #name;
    #basePrice;
    #totalPrice;

    constructor(name, basePrice) {
        this.#name = name;
        this.#basePrice = basePrice;
        this.#totalPrice = 0;
    }

    getName() { return this.#name; }
    getBasePrice() { return this.#basePrice; }
    getTotalPrice() { return this.#totalPrice; }

    setTotalPrice(value) { this.#totalPrice = value; }
}

module.exports = Product;