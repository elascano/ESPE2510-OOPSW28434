import { FarmAnimal } from "./FarmAnimal.js";

export class Pig extends FarmAnimal {
    constructor(foodPerDay, isReadyForSale, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.foodPerDay = foodPerDay;
        this.isReadyForSale = isReadyForSale;
    }

    feed(amount) {
        this.foodPerDay += amount;
    }

    toString() {
        return `
=== PIG ===
Food per Day: ${this.foodPerDay} kg
Ready for Sale: ${this.isReadyForSale}
${super.toString()}
============================`;
    }
}
