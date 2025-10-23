export class Colony {
  constructor(nest) {
    this.nest = nest;
    this.ants = new Set();
  }

  addAnt(ant) {
    this.ants.add(ant);
    ant.colony = this;
  }

  removeAnt(ant) {
    this.ants.delete(ant);
  }

  tick(simulation) {
    while (this.nest.canCreateAnt()) {
      this.nest.createAnt(this);
    }
  }
}