// chicken.js
class Chicken {
  constructor(id, name, color, age, isMolting) {
    this._id = id;
    this._name = name;
    this._color = color;
    this._age = age;
    this._isMolting = isMolting;
  }

  toString() {
    return (
      `\nChicken{\n` +
      `id ->\t\t${this._id},\n` +
      `name ->\t${this._name},\n` +
      `color ->\t${this._color},\n` +
      `age ->\t\t${this._age},\n` +
      `isMolting ->\t${this._isMolting}\n}`
    );
  }

  // Getters and setters
  get id() {
    return this._id;
  }
  set id(value) {
    this._id = value;
  }

  get name() {
    return this._name;
  }
  set name(value) {
    this._name = value;
  }

  get color() {
    return this._color;
  }
  set color(value) {
    this._color = value;
  }

  get age() {
    return this._age;
  }
  set age(value) {
    this._age = value;
  }

  get isMolting() {
    return this._isMolting;
  }
  set isMolting(value) {
    this._isMolting = value;
  }
}

module.exports = Chicken;
