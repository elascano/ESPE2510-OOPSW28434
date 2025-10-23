class Egg {
  constructor() {
    const sizes = ['S', 'M', 'L'];
    this.size = sizes[Math.floor(Math.random() * sizes.length)];
  }

  toString() {
    return `${this.size}-sized egg`;
  }
}

export default Egg;