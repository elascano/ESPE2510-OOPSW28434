export class Cage {
    constructor(id, description, type, location) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.location = location;
    }

    toString() {
        return `Cage {
    ID: ${this.id}
    Description: ${this.description}
    Type: ${this.type}
    Location: ${this.location.toString()}
}`;
    }
}
