class FarmAnimal {
    constructor(id, breed, bornOn, gender, isAbleToReproduce, weight, cage) {
        if (new.target === FarmAnimal) {
            throw new Error("FarmAnimal is an abstract class and cannot be instantiated directly.");
        }

        this.id = id;
        this.breed = breed;
        this.bornOn = bornOn;      
        this.gender = gender;
        this.isAbleToReproduce = isAbleToReproduce;
        this.weight = weight;
        this.cage = cage;
    }

    getAgeInMonths() {
        // TODO: compute the age in months 
        return 0;
    }

    toString() {
        return `{
  "id": ${this.id},
  "breed": "${this.breed}",
  "bornOn": "${this.bornOn}",
  "gender": "${this.gender}",
  "isAbleToReproduce": ${this.isAbleToReproduce},
  "weight": ${this.weight},
  "cage": ${this.cage}
}`;
    }

    assignCage(cage) {
        this.setCage(cage);
    }

    getId() {
        return this.id;
    }
    setId(id) {
        this.id = id;
    }

    getBreed() {
        return this.breed;
    }
    setBreed(breed) {
        this.breed = breed;
    }

    getBornOn() {
        return this.bornOn;
    }
    setBornOn(bornOn) {
        this.bornOn = bornOn;
    }

    getGender() {
        return this.gender;
    }
    setGender(gender) {
        this.gender = gender;
    }

    isIsAbleToReproduce() {
        return this.isAbleToReproduce;
    }
    setIsAbleToReproduce(isAbleToReproduce) {
        this.isAbleToReproduce = isAbleToReproduce;
    }

    getWeight() {
        return this.weight;
    }
    setWeight(weight) {
        this.weight = weight;
    }

    getCage() {
        return this.cage;
    }
    setCage(cage) {
        this.cage = cage;
    }
}

module.exports = FarmAnimal;
