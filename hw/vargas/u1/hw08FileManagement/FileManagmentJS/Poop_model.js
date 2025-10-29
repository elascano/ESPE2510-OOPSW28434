export class Poop {
    
    constructor(amount) {
        this._amount = amount;
    }

    getAmount() {
        return this._amount;
    }

    toString() {
        return `Poop{amount=${this._amount}}`;
    }
}