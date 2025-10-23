class Poop {
    constructor() {
        this._amount = this.generateRandomAmount(1, 10);
    }

    /**
     * Generate a int number between min and max
     * 
     * @param {number} min 
     * @param {number} max 
     */
    generateRandomAmount(min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    get amount() {
        return this._amount;
    }

    set amount(newAmount) {
        this._amount = newAmount;
    }

    toString() {
        return `amount --> ${this._amount}`;
    }
}

module.exports = {
    Poop
};