import { FarmAnimal } from "./FarmAnimal.js";

export class Cow extends FarmAnimal {
    constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage, isProducingMilk, litersPerDay) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isProducingMilk = isProducingMilk;
        this.litersPerDay = litersPerDay;
    }

    toString() {
        return `Cow {
${super.toString()}  Producing Milk: ${this.isProducingMilk}
  Liters Per Day: ${this.litersPerDay}
}`;
    }
}
