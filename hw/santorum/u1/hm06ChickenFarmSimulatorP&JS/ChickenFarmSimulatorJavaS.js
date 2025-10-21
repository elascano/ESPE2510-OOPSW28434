class Chicken {
    constructor(id, name, color, age, isMolting) {
        this._id = id;
        this._name = name;
        this._color = color;
        this._age = age;
        this._isMolting = isMolting;
    }

    get id() { return this._id; }
    get name() { return this._name; }
    get color() { return this._color; }
    get age() { return this._age; }
    get isMolting() { return this._isMolting; }

    set id(value) { this._id = value; }
    set name(value) { this._name = value; }
    set color(value) { this._color = value; }
    set age(value) { this._age = value; }
    set isMolting(value) { this._isMolting = value; }

    toString() {
        return `Chicken{
  id: ${this._id}
  name: ${this._name}
  color: ${this._color}
  age: ${this._age}
  isMolting: ${this._isMolting}
}`;
    }
}

function main() {
    console.log("This is my Chicken Farm Simulator.\n");
    let owner = "Thais Santórum";
    let id = 1;
    let name = "Lucy";
    let color = "White and brown";
    let age = 2;
    let isMolting = false;
    let chicken = new Chicken(id, name, color, age, isMolting);
    console.log(`The chicken is ${chicken.toString()}`);
    console.log(`\nChicken id: ${chicken.id}, name: ${chicken.name}`);
}

main();
