class ChickenFarmer {
  constructor(name) {
    this.name = name;
    this.coops = [];
  }

  addCoop(coop) {
    this.coops.push(coop);
  }

  getAllChickens() {
    return this.coops.flatMap(coop => coop.getChickens());
  }
}