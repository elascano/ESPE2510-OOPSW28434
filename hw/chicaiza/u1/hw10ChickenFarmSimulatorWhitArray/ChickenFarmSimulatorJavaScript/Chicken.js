class Chicken {
    constructor(name = '', age = 0, color = '', molting = false) {
        this._name = name;
        this._age = age;
        this._color = color;
        this._molting = molting;
    }

    // Getters
    get name() { return this._name; }
    get age() { return this._age; }
    get color() { return this._color; }
    get molting() { return this._molting; }

    // Setters
    set name(name) { this._name = name; }
    set age(age) { this._age = age; }
    set color(color) { this._color = color; }
    set molting(molting) { this._molting = molting; }

    displayInfo() {
        return `Chicken: ${this._name}, Age: ${this._age}, Color: ${this._color}, Molting: ${this._molting}`;
    }
}

module.exports = Chicken;