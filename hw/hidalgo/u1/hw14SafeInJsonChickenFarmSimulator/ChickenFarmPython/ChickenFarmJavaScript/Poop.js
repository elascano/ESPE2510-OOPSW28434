class Poop {
    constructor(amount) {
        this.amount = amount;
    }

    toString() {
        return `Poop {amount = ${this.amount}}`;
    }

    getAmount() {
        return this.amount;
    }
}

module.exports = Poop;