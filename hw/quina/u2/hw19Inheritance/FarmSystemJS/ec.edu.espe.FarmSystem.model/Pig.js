import { FarmAnimal } from './FarmAnimal.js';
import { Cage } from './Cage.js';

export class Pig extends FarmAnimal {
  constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage /** @type {Cage} */) {
    super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
  }

  toString() {
    return `Pig(${super.toString()})`;
  }
}
