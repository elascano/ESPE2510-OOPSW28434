class Poop {
    constructor() {
        this.amount = Math.floor(Math.random() * 5) + 1;
    }

    toString() {
        return `amount --> ${this.amount}`;
    }

    getAmount() {
        return this.amount;
    }

    setAmount(amount) {
        this.amount = amount;
    }
}

module.exports = Poop;