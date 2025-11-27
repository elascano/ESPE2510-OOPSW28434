import { FarmAnimal } from './FarmAnimal.js';
import { Cage } from './Cage.js';

export class Sheep extends FarmAnimal {
  constructor(lastShearing, id, breed, bornOn, gender, isAbleToReproduce, weight, cage /** @type {Cage} */) {
    super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
    this.lastShearing = lastShearing; // Date
  }

  toString() {
    return `Sheep(lastShearing=${this.lastShearing.toISOString().slice(0,10)}, ${super.toString()})`;
  }
}
