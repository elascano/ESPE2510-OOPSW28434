import { FarmAnimal } from './FarmAnimal.js';

export class Sheep extends FarmAnimal {
    constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage, lastShearing) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.lastShearing = lastShearing; // Objeto Date
    }

    cutWool() {
        this.shear();
    }

    shear() {
        this.lastShearing = new Date(); // Actualiza la fecha de esquilado a hoy
        console.log("La oveja ha sido esquilada.");
    }
}