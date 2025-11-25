import { FarmAnimal } from "./FarmAnimal.js";

export class Chicken extends FarmAnimal {
    constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage, isMolting, laidEggs) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isMolting = isMolting;
        this.laidEggs = laidEggs;
    }

    toString() {
        return `Chicken {
${super.toString()}  Is Molting: ${this.isMolting}
  Eggs Laid: ${this.laidEggs}
}`;
    }
}
