export class FarmAnimal {
    constructor(name, age, weight) {
        if (new.target === FarmAnimal) {
            throw new Error("FarmAnimal is an abstract class and cannot be instantiated directly.");
        }
        this.name = name;
        this.age = age;
        this.weight = weight;
    }


    makeSound() {
        throw new Error("makeSound() must be implemented in derived classes.");
    }

    getInfo() {
        return `${this.name} - Age: ${this.age}, Weight: ${this.weight}`;
    }
}
