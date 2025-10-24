// ============================================
// Egg Class
// Author: Josue Rojas
// Owner: Josue Rojas
// ============================================

class Egg {
  constructor(size) {
    this.size = size;
  }

  toString() {
    const sizes = { S: "small", M: "medium", L: "large" };
    return `${sizes[this.size] || "unknown"} egg`;
  }
}

module.exports = Egg;