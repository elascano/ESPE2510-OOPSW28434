export class Poop {
  constructor(quantity) {
    this.quantity = quantity;
  }

  toString() {
    return `Poop(quantity=${this.quantity})`;
  }

  toJSON() {
    return {
      quantity: this.quantity
    };
  }
}
