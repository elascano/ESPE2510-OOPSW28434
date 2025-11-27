import { FarmAnimal } from "./FarmAnimal.js";

export class Chicken extends FarmAnimal {
    constructor(isMolting, laidEggs, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isMolting = isMolting;
        this.laidEggs = laidEggs;
    }

    layAnEgg() {
        this.laidEggs++;
    }

    toString() {
    return `
=== CHICKEN ===
Molting: ${this.isMolting}
Laid Eggs: ${this.laidEggs}
ID: ${this.id}
Breed: ${this.breed}
Born On: ${this.bornOn.toDateString()}
Gender: ${this.gender}
Able to Reproduce: ${this.isAbleToReproduce}
Weight: ${this.weight} kg
Cage: ${this.cage.description}
============================`;
}

}
