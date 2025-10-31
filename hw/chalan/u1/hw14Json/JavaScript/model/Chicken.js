import Poop from './Poop.js';
import Egg from './Egg.js';

export default class Chicken {
    #id; #name; #color; #age; #isMolting;

    constructor(id, name, color, age, isMolting) {
        this.#id = id;
        this.#name = name;
        this.#color = color;
        this.#age = age;
        this.#isMolting = isMolting;
    }

    getId() { return this.#id; }
    getName() { return this.#name; }
    getColor() { return this.#color; }
    getAge() { return this.#age; }
    isMolting() { return this.#isMolting; }

    toString() {
        return `Chicken{id: ${this.#id}\t name: ${this.#name}\t color: ${this.#color}\t age: ${this.#age}\t isMolting: ${this.#isMolting}}`;
    }

    toJSON() {
        return {
            id: this.#id,
            name: this.#name,
            color: this.#color,
            age: this.#age,
            isMolting: this.#isMolting
        };
    }

    static fromJSON(data) {
        return new Chicken(data.id, data.name, data.color, data.age, data.isMolting);
    }

    poop(amount) {
        const poopInstance = new Poop(amount);
        console.log(`Chicken ${this.#name} is pooping a ${poopInstance}`);
        return poopInstance;
    }

    layAnEgg(size) {
        const egg = new Egg(size);
        console.log(`Chicken ${this.#name} is laying a ${egg.getSize()} size egg`);
        return egg;
    }

    doStuff() {
        this.cluck();
        this.eat();
        this.cluck();
        this.poop(2);
        this.poop(3);
        this.eat();
        this.wander();
        this.drink();
        this.layAnEgg('M');
        this.layAnEgg('L');
    }

    cluck() { console.log(`Chicken ${this.#name} is clucking, cluck, cluck, cluck`); }
    eat() { console.log(`Chicken ${this.#name} is eating`); }
    wander() { console.log(`Chicken ${this.#name} is wandering`); }
    drink() { console.log(`Chicken ${this.#name} is drinking`); }
}