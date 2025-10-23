// ============================================
// Poop Class
// Author: Josue Rojas
// Owner: Josue Rojas
// ============================================

class Poop {
  constructor(amount) {
    this.amount = amount;
  }

  toString() {
    return this.amount > 3 ? "Poop size: big" : "Poop size: small";
  }
}

module.exports = Poop;