class Poop {
    constructor(quantity = 1) {
        this.quantity = quantity;
    }

    toString() {
        return `Poop(quantity=${this.quantity})`;
    }
}

module.exports = Poop;
