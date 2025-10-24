// chicken.js
export class Chicken {
  constructor(name) {
    // Stores the chicken's name
    this.name = name;
  }

  getName() {
    // Returns the chicken's name
    return this.name;
  }

  toString() {
    // How to display the chicken
    return this.name;
  }
}
