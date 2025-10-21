class Chicken {
  constructor(id, name, color, age, isMolting) {
    this.id = id;
    this.owner = "Arelys Otavalo";
    this.name = name;
    this.color = color;
    this.age = age;
    this.isMolting = isMolting;
  }

  setId(id) { this.id = id; }
  getId() { return this.id; }

  setOwner(owner) { this.owner = owner; }
  getOwner() { return this.owner; }

  setName(name) { this.name = name; }
  getName() { return this.name; }

  setColor(color) { this.color = color; }
  getColor() { return this.color; }

  setAge(age) { this.age = age; }
  getAge() { return this.age; }

  setIsMolting(isMolting) { this.isMolting = isMolting; }
  getIsMolting() { return this.isMolting; }

  toString() {
    return `Chicken Information:
  Id         --> ${this.id}
  Name       --> ${this.name}
  Color      --> ${this.color}
  Age        --> ${this.age}
  Is Molting --> ${this.isMolting}`;
  }
}

module.exports = Chicken;

