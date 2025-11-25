import { FarmAnimal } from "./FarmAnimal.js";

export class Sheep extends FarmAnimal {
    constructor(lastShearing, woolKg, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.lastShearing = lastShearing;
        this.woolKg = woolKg;
    }

    cutWool() {
        this.woolKg = 0;
    }

    toString() {
        return `Sheep{lastShearing=${this.lastShearing}, woolKg=${this.woolKg}, ${super.toString()}}`;
    }
}
