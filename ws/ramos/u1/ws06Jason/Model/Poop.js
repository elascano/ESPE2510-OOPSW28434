export default class Poop {
    #amount;

    constructor(amount) {
        this.#amount = amount;
    }

    getAmount() { return this.#amount; }
    setAmount(amount) { this.#amount = amount; }

    toString() {
        return `Poop{amount=${this.#amount}}`;
    }
}
