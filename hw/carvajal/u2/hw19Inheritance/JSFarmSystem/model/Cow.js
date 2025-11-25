import { FarmAnimal } from "./FarmAnimal.js";

export class Cow extends FarmAnimal {
    constructor(isProducingMilk, litersADay, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isProducingMilk = isProducingMilk;
        this.litersADay = litersADay;
    }

    toString() {
        return `Cow{isProducingMilk=${this.isProducingMilk}, litersADay=${this.litersADay}, ${super.toString()}}`;
    }
}
