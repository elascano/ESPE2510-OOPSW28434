export class ChickenFarmer {
    #name;
    #coops;
    #coopIteratorIndex = 0;

    constructor(name) {
        this.#name = name;
        this.#coops = [];
    }

    add(coop) {
        if (coop) {
            this.#coops.push(coop);
        }
    }

    remove(coopId) {
        const initialLength = this.#coops.length;
        this.#coops = this.#coops.filter(coop => coop.id !== coopId);
        return this.#coops.length < initialLength;
    }

    resetIteration() {
        this.#coopIteratorIndex = 0;
    }

    next() {
        if (this.#coopIteratorIndex < this.#coops.length) {
            const nextCoop = this.#coops[this.#coopIteratorIndex];
            this.#coopIteratorIndex++;
            return nextCoop;
        }
        return null;
    }

    getName() {
        return this.#name;
    }
}