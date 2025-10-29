export default class Egg {
    #size;

    constructor(size) {
        this.#size = size;
    }

    getSize() { return this.#size; }
    setSize(size) { this.#size = size; }

    toString() {
        return `Egg{size=${this.#size}}`;
    }
}
