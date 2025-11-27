import { FarmAnimal } from './FarmAnimal.js';
import { Cage } from './Cage.js';

export class Chicken extends FarmAnimal {
  constructor(isMolting, laidEggs, id, breed, bornOn, gender, isAbleToReproduce, weight, cage /** @type {Cage} */) {
    super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
    this.isMolting = isMolting;
    this.laidEggs = laidEggs;
  }

  toString() {
    return `Chicken(isMolting=${this.isMolting}, laidEggs=${this.laidEggs}, ${super.toString()})`;
  }
}
