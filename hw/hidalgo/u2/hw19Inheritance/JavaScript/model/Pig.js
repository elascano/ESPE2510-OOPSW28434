import { FarmAnimal } from "./FarmAnimal.js";

export class Pig extends FarmAnimal {
    constructor(name, age, weight, breed) {
        super(name, age, weight);
        this.breed = breed;
    }

    makeSound() {
        return `${this.name} says: Oink oink!`;
    }
}

