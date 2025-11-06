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
    setId(id) { this.#id = id; }

    getName() { return this.#name; }
    setName(name) { this.#name = name; }

    getColor() { return this.#color; }
    setColor(color) { this.#color = color; }

    getAge() { return this.#age; }
    setAge(age) { this.#age = age; }

    isMolting() { return this.#isMolting; }
    setIsMolting(isMolting) { this.#isMolting = isMolting; }

    toString() {
        return `Chicken{id: ${this.#id}\t name: ${this.#name}\t color: ${this.#color}\t age: ${this.#age}\t isMolting: ${this.#isMolting}}`;
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
