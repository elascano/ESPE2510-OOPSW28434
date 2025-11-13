export class Egg {
  constructor(size) {
    this.size = size; // 'S', 'M', 'L'
  }

  getSize() {
    return this.size;
  }

  toString() {
    return `Egg(size=${this.size})`;
  }

  toJSON() {
    return {
      size: this.size
    };
  }
}
