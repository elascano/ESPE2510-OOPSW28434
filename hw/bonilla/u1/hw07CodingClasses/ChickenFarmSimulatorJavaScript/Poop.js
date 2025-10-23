class Poop {
    constructor(amount) {
        this.amount = amount;
    }

    toString() {
        return `Poop{amount=${this.amount}}`;
    }

    getAmount() {
        return this.amount;
    }

    setAmount(amount) {
        this.amount = amount;
    }
}