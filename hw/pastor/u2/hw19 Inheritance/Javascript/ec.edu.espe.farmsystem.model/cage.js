class Cage {
    constructor(id, description, type, location) {
        this.id = id;
        this.description = description;
        this.type = type; // 1 coop, 2 stable, 3 pens
        this.location = location;
    }

    toString() {
        return `[${this.id}] ${this.description} (${this.location})`;
    }
}

module.exports = Cage;