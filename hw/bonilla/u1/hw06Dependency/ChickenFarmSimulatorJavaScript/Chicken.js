class Chicken {
    constructor(id, name, color, age, isMolting) {
        this._id = id;
        this._name = name;
        this._color = color;
        this._age = age;
        this._isMolting = isMolting;
    }

    get_id() {
        return this._id;
    }

    get_name() {
        return this._name;
    }

    get_color() {
        return this._color;
    }

    get_age() {
        return this._age;
    }

    get_isMolting() {
        return this._isMolting;
    }

    set_id(id) {
        this._id = id;
    }

    set_name(name) {
        this._name = name;
    }

    set_color(color) {
        this._color = color;
    }

    set_age(age) {
        this._age = age;
    }

    set_isMolting(isMolting) {
        this._isMolting = isMolting;
    }

    toString() {
        return `chicken(id --> ${this._id},\n` +
               `name -->      ${this._name},\n` +
               `color ->     ${this._color},\n` +
               `age --->       ${this._age},\n` +
               `isMolting -> ${this._isMolting})`;
    }
}
module.exports = Chicken;