class Egg {
  constructor(id, chickenId, laidAt = new Date()) {
    this.id = id; this.chickenId = chickenId; this.laidAt = laidAt;
  }
  toString() { return `Egg#${this.id} from Chicken#${this.chickenId} @ ${this.laidAt.toISOString()}`; }
}
export { Egg };
