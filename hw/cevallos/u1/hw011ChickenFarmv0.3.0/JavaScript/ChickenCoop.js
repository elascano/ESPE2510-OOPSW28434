const fs = require('fs');
const Chicken = require('./Chicken');

class ChickenCoop {
  constructor(id, name) {
    this.id = id;
    this.name = name;
    this.chickens = []; // Esto es como el ArrayList<Chicken> en Java
  }

  addChicken(chicken) {
    if (chicken instanceof Chicken) {
      this.chickens.push(chicken);
      console.log(`Chicken ${chicken.name} added to coop ${this.name}`);
    } else {
      console.log("Only Chicken objects can be added to the coop!");
    }
  }

  removeChickenById(id) {
    const index = this.chickens.findIndex(c => c.id === id);
    if (index !== -1) {
      const removed = this.chickens.splice(index, 1)[0];
      console.log(`Chicken ${removed.name} removed from coop ${this.name}`);
    } else {
      console.log(`No chicken found with ID ${id}`);
    }
  }

  countChickens() {
    return this.chickens.length;
  }

  listChickens() {
    console.log(`\n--- Chickens in Coop ${this.name} ---`);
    this.chickens.forEach(chicken => console.log(chicken.toString()));
  }

  saveCoopToCSV(filename = 'chicken_coops.csv') {
    const data = this.chickens
      .map(c => `${this.id},${this.name},${c.id},${c.name},${c.color},${c.age},${c.isMolting}`)
      .join('\n') + '\n';
    fs.appendFileSync(filename, data);
    console.log(`Coop ${this.name} saved to ${filename}`);
  }
}

module.exports = ChickenCoop;
