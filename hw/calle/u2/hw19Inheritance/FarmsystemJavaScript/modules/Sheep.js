import { FarmAnimal } from './FarmAnimal.js';

export class Sheep extends FarmAnimal {
    #lastSheering;

    constructor(lastSheering, id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.#lastSheering = lastSheering;
    }

    cutWhool() {
        console.log("Cutting wool from the sheep...");
        this.shear();
    }

    shear() {
        this.#lastSheering = new Date();
        console.log(`Sheep shorn. New shearing date: ${this.#lastSheering.toDateString()}`);
    }

    toString() {
        return `Sheep{lastSheering=${this.#lastSheering.toDateString()}, ${super.toString()}}`;
    }

    getLastSheering() {
        return this.#lastSheering;
    }

    setLastSheering(lastSheering) {
        this.#lastSheering = lastSheering;
    }
}