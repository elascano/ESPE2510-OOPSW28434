import { Location } from './Location.js';

export class Cage {
    #id;
    #description;
    #type;
    #location;

    constructor(id, description, type, location) {
        this.#id = id;
        this.#description = description;
        this.#type = type;
        this.#location = location;
    }

    toString() {
        return `Cage{id=${this.#id}, description=${this.#description}, type=${this.#type}, location=${this.#location.toString()}}`;
    }

    getId() {
        return this.#id;
    }

    setId(id) {
        this.#id = id;
    }

    getDescription() {
        return this.#description;
    }

    setDescription(description) {
        this.#description = description;
    }

    getType() {
        return this.#type;
    }

    setType(type) {
        this.#type = type;
    }

    getLocation() {
        return this.#location;
    }

    setLocation(location) {
        this.#location = location;
    }
}