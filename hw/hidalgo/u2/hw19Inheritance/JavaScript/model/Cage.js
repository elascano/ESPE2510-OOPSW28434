export class Cage {
    constructor(id, capacity, location) {
        this.id = id;
        this.capacity = capacity;
        this.location = location;
        this.animals = [];
    }

    addAnimal(animal) {
        if (this.animals.length >= this.capacity) {
            return `Cage ${this.id} is full!`;
        }
        this.animals.push(animal);
        return `${animal.name} added to cage ${this.id}.`;
    }

    listAnimals() {
        return this.animals.map(a => a.getInfo());
    }
}

