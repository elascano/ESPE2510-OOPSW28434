export class Food {
  constructor(amount) {
    this.amount = amount; // mg
  }

  isEmpty() {
    return this.amount <= 0;
  }
}