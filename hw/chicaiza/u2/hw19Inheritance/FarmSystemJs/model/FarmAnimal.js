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

    toString() {
        return `  ID: ${this.id}
  Breed: ${this.breed}
  Born on: ${this.bornOn}
  Gender: ${this.gender}
  Can Reproduce: ${this.isAbleToReproduce}
  Weight: ${this.weight} kg
  Cage: ${this.cage.toString()}
`;
    }
}
