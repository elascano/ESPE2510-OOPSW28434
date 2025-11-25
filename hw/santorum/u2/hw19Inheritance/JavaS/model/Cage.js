export class Cage {
    constructor(id, description, type) {
        this.id = id;
        this.description = description;
        this.type = type; // 1 coop, 2 stable, 3 pens
    }

    toString() {
        return `Cage{id: ${this.id}, description: ${this.description}, type: ${this.type}}`;
    }
}
