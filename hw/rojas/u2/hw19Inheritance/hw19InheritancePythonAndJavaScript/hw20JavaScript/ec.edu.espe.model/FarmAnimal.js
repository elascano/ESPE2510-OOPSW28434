import { Cage } from './Cage.js';

export class FarmAnimal {
    constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        this.id = id;
        this.breed = breed;
        this.bornOn = bornOn; // Debe ser un objeto Date de JS
        this.gender = gender;
        this.isAbleToReproduce = isAbleToReproduce;
        this.weight = weight;
        this.cage = cage; // Espera un objeto de tipo Cage
    }

    getAgeInMonths() {
        const today = new Date();
        // bornOn debe ser un objeto Date. Si viene como string, asegúrate de convertirlo.
        let months = (today.getFullYear() - this.bornOn.getFullYear()) * 12;
        months -= this.bornOn.getMonth();
        months += today.getMonth();
        
        // Ajuste si el día actual es menor al día de nacimiento
        if (today.getDate() < this.bornOn.getDate()) {
            months--;
        }
        
        return months <= 0 ? 0 : months;
    }
}