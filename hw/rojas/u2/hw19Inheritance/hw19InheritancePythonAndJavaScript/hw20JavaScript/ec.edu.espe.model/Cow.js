import { FarmAnimal } from './FarmAnimal.js';

export class Cow extends FarmAnimal {
    constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage, isProducingMilk, littersADay) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isProducingMilk = isProducingMilk;
        this.littersADay = littersADay;
    }

    milk() {
        if (this.isProducingMilk) {
            console.log(`Ordeñando... Se obtuvieron ${this.littersADay} litros.`);
            return this.littersADay;
        } else {
            console.log("Esta vaca no está produciendo leche actualmente.");
            return 0;
        }
    }
}