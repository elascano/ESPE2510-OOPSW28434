class Cage {
    constructor(id, description, type, location) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.location = location;
    }
    toString() { return `Cage{id=${this.id}, desc=${this.description}, loc=${this.location.toString()}}`; }
}
module.exports = Cage;