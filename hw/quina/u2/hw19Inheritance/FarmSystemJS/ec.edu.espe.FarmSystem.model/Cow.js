import { FarmAnimal } from './FarmAnimal.js';
import { Cage } from './Cage.js';

export class Cow extends FarmAnimal {
  constructor(isProducingMilk, litersADay, id, breed, bornOn, gender, isAbleToReproduce, weight, cage /** @type {Cage} */) {
    super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
    this.isProducingMilk = isProducingMilk;
    this.litersADay = litersADay;
  }

  toString() {
    return `Cow(isProducingMilk=${this.isProducingMilk}, litersADay=${this.litersADay}, ${super.toString()})`;
  }
}
