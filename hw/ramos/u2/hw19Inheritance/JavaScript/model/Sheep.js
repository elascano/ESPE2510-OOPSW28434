import { FarmAnimal } from "./FarmAnimal.js";

export class Sheep extends FarmAnimal {
    constructor(lastSheering, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.lastSheering = lastSheering;
    }

    shear() {
        this.lastSheering = new Date();
    }

    toString() {
        return `
-- Sheep --
Last Sheering: ${this.lastSheering.toDateString()}
${super.toString()}
-----------------------------`;
    }
}

