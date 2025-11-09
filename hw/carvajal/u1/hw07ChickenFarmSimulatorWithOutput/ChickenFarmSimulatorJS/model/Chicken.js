import Egg from './Egg.js';
import Poop from './Poop.js';

class Chicken {
  constructor(id, name, color, isMolting = false) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.isMolting = isMolting;
  }

  showData() {
    console.log(`\nname -> ${this.name}`);
    console.log(`color -> ${this.color}`);
    console.log(`isMolting -> ${this.isMolting}`);
  }

  doRoutine() {
    console.log(`\nChicken ${this.name} is clucking, cluck cluck cluck`);
    console.log(`Chicken ${this.name} is eating grains`);
    const poop = new Poop();
    console.log(`Chicken ${this.name} is pooping -> ${poop}`);
    console.log(`Chicken ${this.name} is walking`);
    console.log(`Chicken ${this.name} is drinking`);
    const egg = new Egg();
    console.log(`Chicken ${this.name} is laying a ${egg}`);
  }
}

export default Chicken;