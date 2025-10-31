class Poop {
  constructor(id, chickenId) {
    this.id = id;
    this.chickenId = chickenId;
  }

  toString() {
    return `Poop#${this.id} from Chicken#${this.chickenId}`;
  }
}

export { Poop };
