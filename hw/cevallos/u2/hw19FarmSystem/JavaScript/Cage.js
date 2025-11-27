class Cage {
    constructor(id, description, type, location) {
        this.id = id;
        this.description = description;
        this.type = type; // 1=coop, 2=table, 3=pens
        this.location = location;
    }

    toString() {
        return `Cage{id=${this.id}, description=${this.description}, type=${this.type}, location=${this.location}}`;
    }
}

module.exports = Cage;