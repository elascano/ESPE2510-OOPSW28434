class Poop {
  constructor(chickenId, weightGrams) {
    this.chicken_id = chickenId;
    this.weight_grams = weightGrams;
  }
  toString() {
    return `Poop(from=${this.chicken_id}, weight=${this.weight_grams}g)`;
  }
}

module.exports = Poop;
