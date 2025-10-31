export default class Farmer {
    #id;
    #name;
    #coopIds;

    constructor(id, name) {
        this.#id = id;
        this.#name = name;
        this.#coopIds = [];
    }

    getId() { return this.#id; }
    getName() { return this.#name; }
    getCoopIds() { return this.#coopIds; }

    addCoop(coopId) {
        if (!this.#coopIds.includes(coopId)) {
            this.#coopIds.push(coopId);
        }
    }

    toJSON() {
        return {
            id: this.#id,
            name: this.#name,
            coopIds: this.#coopIds
        };
    }

    static fromJSON(data) {
        const farmer = new Farmer(data.id, data.name);
        farmer.#coopIds = data.coopIds || [];
        return farmer;
    }

    toString() {
        return `Farmer{id: ${this.#id}, name: ${this.#name}, coops: ${this.#coopIds.length}}`;
    }
}