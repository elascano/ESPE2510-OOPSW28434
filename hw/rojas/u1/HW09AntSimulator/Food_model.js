// Owner/Author: Josue Rojas
export class Food {
  constructor(amountMg) { this.amountMg = Math.max(0, Math.floor(amountMg)); }
  getAmount() { return this.amountMg; }
}
