export class ChickenFarmer {
  constructor(name) {
    this.name = name;
  }

  feedChicken(chicken) {
    console.log(`${this.name} fed ${chicken.name}`);
  }

  cleanCoop() {
    console.log(`${this.name} cleaned the coop.`);
  }
}