class ChickenCoop {
  constructor(id) {
    this.id = id;
    this.chickens = [];
  }

  add(chicken) {
    if (this.chickens.some(c => c.id === chicken.id)) return false;
    this.chickens.push(chicken);
    return true;
  }

  remove(chickenId) {
    const idx = this.chickens.findIndex(c => c.id === chickenId);
    if (idx === -1) return false;
    this.chickens.splice(idx, 1);
    return true;
  }

  find(chickenId) {
    return this.chickens.find(c => c.id === chickenId) || null;
  }
}

module.exports = ChickenCoop;
