class Poop {
    constructor(amount) {
        this._amount = amount;
    }

    toString() {
        return `Poop{amount = ${this._amount}}`;
    }

    get amount() {
        return this._amount;
    }
}

module.exports = Poop;