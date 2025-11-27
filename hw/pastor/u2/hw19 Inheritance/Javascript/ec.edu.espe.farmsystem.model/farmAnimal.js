class FarmAnimal {
    constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage, location) {
        this.id = id;
        this.breed = breed;
        this.bornOn = bornOn;
        this.gender = gender;
        this.isAbleToReproduce = isAbleToReproduce;
        this.weight = weight;
        this.cage = cage;
        this.location = location;
    }

    getAgeInMonths() {
        const today = new Date();
        let months = (today.getFullYear() - this.bornOn.getFullYear()) * 12;
        months -= this.bornOn.getMonth();
        months += today.getMonth();
        return months <= 0 ? 0 : months;
    }

    toString() {
        const typeName = this.constructor.name.toUpperCase();
        const reproStr = this.isAbleToReproduce ? "Yes" : "No";
        const cageStr = this.cage ? this.cage.toString() : "No Cage Assigned";
        const dateStr = this.bornOn.toISOString().split('T')[0];

        return `
========================================
           FARM ANIMAL: ${typeName}
========================================
 ID              : ${this.id}
 Breed           : ${this.breed}
 Gender          : ${this.gender}
 Weight          : ${this.weight.toFixed(1)} kg
 Born On         : ${dateStr}
 Reproduces      : ${reproStr}
 Location        : ${this.location}
 Cage            : ${cageStr}`;
    }
}

module.exports = FarmAnimal;