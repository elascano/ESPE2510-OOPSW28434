// Cage.js
export class Cage {
    constructor(id, description, type, location) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.location = location;
    }

    toString() {
        return `Cage{id=${this.id}, description=${this.description}, type=${this.type}, location=${this.location}}`;
    }
}
