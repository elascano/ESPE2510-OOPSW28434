import { FarmAnimal } from './FarmAnimal.js';

export class Chicken extends FarmAnimal {
    #isMolting;
    #laidEggs;

    constructor(isMolting, laidEggs, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.#isMolting = isMolting;
        this.#laidEggs = laidEggs;
    }

    layAnEgg() {
        this.setLaidEggs(this.getLaidEggs() + 1);
        console.log("Chicken laid an egg!");
    }

    toString() {
        return `Chicken{isMolting=${this.#isMolting}, laidEggs=${this.#laidEggs}, ${super.toString()}}`;
    }

    isIsMolting() {
        return this.#isMolting;
    }

    setIsMolting(isMolting) {
        this.#isMolting = isMolting;
    }

    getLaidEggs() {
        return this.#laidEggs;
    }

    setLaidEggs(laidEggs) {
        this.#laidEggs = laidEggs;
    }
}