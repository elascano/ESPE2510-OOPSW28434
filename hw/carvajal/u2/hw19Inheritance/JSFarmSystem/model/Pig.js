import { FarmAnimal } from "./FarmAnimal.js";

export class Pig extends FarmAnimal {
    constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
    }

    toString() {
        return `Pig{${super.toString()}}`;
    }
}
