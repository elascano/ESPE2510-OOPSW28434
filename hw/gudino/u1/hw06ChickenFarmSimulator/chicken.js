
class Chicken {
    constructor(id, name, color, age, isMolting) {
        this._id = id;
        this._name = name;
        this._color = color;
        this._age = age;
        this._isMolting = isMolting;
    }

    // Getters
    get id() {
        return this._id;
    }

    get name() {
        return this._name;
    }

    get color() {
        return this._color;
    }

    get age() {
        return this._age;
    }

    get isMolting() {
        return this._isMolting;
    }

    // Setters
    set id(value) {
        this._id = value;
    }

    set name(value) {
        this._name = value;
    }

    set color(value) {
        this._color = value;
    }

    set age(value) {
        this._age = value;
    }

    set isMolting(value) {
        this._isMolting = value;
    }

    toString() {
        return `Chicken:
  id --> ${this._id}
  name --> ${this._name}
  color --> ${this._color}
  age --> ${this._age}
  isMolting --> ${this._isMolting}`;
    }
}


module.exports = Chicken;
