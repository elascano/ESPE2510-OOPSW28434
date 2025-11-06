export class ChickenFarmer {
  constructor(name) {
    this.name = name;
    this.coops = [];
  }

  addCoop(coop) {
    this.coops.push(coop);
  }

  getCoops() { return this.coops; }

  getName() { return this.name; }
}
