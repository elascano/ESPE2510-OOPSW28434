export class FarmAnimal {
    constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        this.id = id;
        this.breed = breed;
        this.bornOn = bornOn;
        this.gender = gender;
        this.isAbleToReproduce = isAbleToReproduce;
        this.weight = weight;
        this.cage = cage;
    }

    getAgeInMonths() {
        const now = new Date();
        const diff = (now - this.bornOn) / (1000 * 60 * 60 * 24 * 30);
        return Math.floor(diff);
    }

    assignCage(cage) {
        this.cage = cage;
    }

    toString() {
        return `FarmAnimal{id=${this.id}, breed='${this.breed}', bornOn=${this.bornOn}, gender=${this.gender}, isAbleToReproduce=${this.isAbleToReproduce}, weight=${this.weight}, cage=${this.cage}}`;
    }
}
