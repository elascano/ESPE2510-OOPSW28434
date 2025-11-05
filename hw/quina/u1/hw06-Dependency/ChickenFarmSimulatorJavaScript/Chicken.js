class Chicken {
  constructor(id, name, color, isMolting, age) {
    this._id = id;
    this._name = name;
    this._color = color;
    this._isMolting = isMolting;
    this._age = age;
  }

  get id() {
    return this._id;
  }

  get name() {
    return this._name;
  }

  get color() {
    return this._color;
  }

  get isMolting() {
    return this._isMolting;
  }

  get age() {
    return this._age;
  }

  set id(value) {
    this._id = value;
  }

  set name(value) {
    this._name = value;
  }

  set color(value) {
    this._color = value;
  }

  set isMolting(value) {
    this._isMolting = value;
  }

  set age(value) {
    this._age = value;
  }

  toString() {
    return `Chicken {
      id --> ${this.id},
      name --> ${this.name},
      color --> ${this.color},
      isMolting --> ${this.isMolting},
      age --> ${this.age}
    }`;
  }
}

module.exports = Chicken;