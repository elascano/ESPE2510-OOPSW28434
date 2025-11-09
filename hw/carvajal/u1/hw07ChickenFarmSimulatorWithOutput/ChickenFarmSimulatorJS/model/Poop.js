class Poop {
  constructor() {
    this.amount = Math.floor(Math.random() * 5) + 1;
  }

  toString() {
    return `Poop(amount = ${this.amount})`;
  }
}

export default Poop;