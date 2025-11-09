// ============================================
// Poop Class
// Author: Mikael Hidalgo 
// Owner: Mikael Hidalgo 
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