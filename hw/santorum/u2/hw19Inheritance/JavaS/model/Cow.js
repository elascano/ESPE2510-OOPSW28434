import { FarmAnimal } from "./FarmAnimal.js";

export class Cow extends FarmAnimal {
    constructor(isProducingMilk, milkQuantityPerDay, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isProducingMilk = isProducingMilk;
        this.milkQuantityPerDay = milkQuantityPerDay;
    }

    milk() {
        return this.isProducingMilk ? this.milkQuantityPerDay : 0;
    }

    toString() {
        return `
=== COW ===
Producing Milk: ${this.isProducingMilk}
Milk per Day: ${this.milkQuantityPerDay} L
${super.toString()}
============================`;
    }
}
