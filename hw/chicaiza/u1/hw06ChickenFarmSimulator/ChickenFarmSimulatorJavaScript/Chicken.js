class Chicken {
    constructor(name = '', age = 0, color = '') {
        this._name = name;
        this._age = age;
        this._color = color;
    }

    // Getters
    get name() { return this._name; }
    get age() { return this._age; }
    get color() { return this._color; }

    // Setters
    set name(name) { this._name = name; }
    set age(age) { this._age = age; }
    set color(color) { this._color = color; }

    displayInfo() {
        return `Chicken: ${this._name}, Age: ${this._age}, Color: ${this._color}`;
    }
}

module.exports = Chicken;