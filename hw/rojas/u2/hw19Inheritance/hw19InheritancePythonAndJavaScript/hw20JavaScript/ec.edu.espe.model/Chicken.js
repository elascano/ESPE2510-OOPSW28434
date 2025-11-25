import { FarmAnimal } from './FarmAnimal.js';

export class Chicken extends FarmAnimal {
    constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage, isMolting, layedEggs) {
        // Pasamos los atributos comunes al padre
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isMolting = isMolting;
        this.layedEggs = layedEggs;
    }

    layAnEgg() {
        this.layedEggs += 1;
        console.log(`La gallina ha puesto un huevo. Total: ${this.layedEggs}`);
    }
}