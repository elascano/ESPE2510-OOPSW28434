<<<<<<< HEAD
class Cage {
    constructor(id, description, type, location) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.location = location;
    }

    toString() {
        return `Cage\nid.: ${this.id}\ndescription: ${this.description}\ntype: ${this.type}`;
    }

    getId() {
        return this.id;
    }

    setId(id) {
        this.id = id;
    }

    getDescription() {
        return this.description;
    }

    setDescription(description) {
        this.description = description;
    }

    getType() {
        return this.type;
    }

    setType(type) {
        this.type = type;
    }
}

=======
class Cage {
    constructor(id, description, type, location) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.location = location;
    }

    toString() {
        return `Cage\nid.: ${this.id}\ndescription: ${this.description}\ntype: ${this.type}`;
    }

    getId() {
        return this.id;
    }

    setId(id) {
        this.id = id;
    }

    getDescription() {
        return this.description;
    }

    setDescription(description) {
        this.description = description;
    }

    getType() {
        return this.type;
    }

    setType(type) {
        this.type = type;
    }
}

>>>>>>> 203b676d8f105a34d549ec251a59c11aa2c57532
module.exports = Cage;