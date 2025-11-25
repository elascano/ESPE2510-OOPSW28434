import { Cage } from './Cage.js';

export class FarmAnimal {
  constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage /** @type {Cage} */) {
    this.id = id;
    this.breed = breed;
    this.bornOn = bornOn;            // Date
    this.gender = gender;
    this.isAbleToReproduce = isAbleToReproduce;
    this.weight = weight;
    this.cage = cage;
  }

  toString() {
    return `FarmAnimal(id=${this.id}, breed='${this.breed}', bornOn=${this.bornOn.toISOString().slice(0,10)}, ` +
           `gender='${this.gender}', isAbleToReproduce=${this.isAbleToReproduce}, weight=${this.weight}, cage=${this.cage})`;
  }
}
