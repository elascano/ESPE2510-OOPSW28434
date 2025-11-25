const { Location } = require('./Location.js');

class Cage {
    constructor(id, description, type, location) {
        this._id = id;
        this._description = description;
        this._type = type;
        this._location = location;
    }

    get id() { return this._id; }
    get description() { return this._description; }
    get type() { return this._type; }
    get location() { return this._location; }

    set id(id) { this._id = id; }
    set description(description) { this._description = description; }
    set type(type) { this._type = type; }
    set location(location) { this._location = location; }

    toString() {
        return `Cage{id=${this._id}, description='${this._description}', ` +
               `type=${this._type}, location=${this._location}}`;
    }
}

module.exports = { Cage };