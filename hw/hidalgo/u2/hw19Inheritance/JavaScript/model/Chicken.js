import { FarmAnimal } from "./FarmAnimal.js";

export class Chicken extends FarmAnimal {
    constructor(name, age, weight, eggProduction) {
        super(name, age, weight);
        this.eggProduction = eggProduction;
    }

    makeSound() {
        return `${this.name} says: Cluck cluck!`;
    }
}
