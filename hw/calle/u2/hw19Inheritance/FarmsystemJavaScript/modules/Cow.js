import { FarmAnimal } from './FarmAnimal.js';

export class Cow extends FarmAnimal {
    #isProducingMilk;
    #littersADay;

    constructor(isProducingMilk, littersADay, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.#isProducingMilk = isProducingMilk;
        this.#littersADay = littersADay;
    }

    milk() {
        if (this.#isProducingMilk) {
            console.log(`Cow milked: ${this.#littersADay} liters.`);
            return this.#littersADay;
        } else {
            console.log("The cow is not producing milk at this time.");
            return 0.0;
        }
    }

    toString() {
        return `Cow{isProducingMilk=${this.#isProducingMilk}, littersADay=${this.#littersADay}, ${super.toString()}}`;
    }

    isIsProducingMilk() {
        return this.#isProducingMilk;
    }

    setIsProducingMilk(isProducingMilk) {
        this.#isProducingMilk = isProducingMilk;
    }

    getLittersADay() {
        return this.#littersADay;
    }

    setLittersADay(littersADay) {
        this.#littersADay = littersADay;
    }
}