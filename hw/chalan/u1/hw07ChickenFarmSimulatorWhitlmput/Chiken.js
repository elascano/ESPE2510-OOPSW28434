
import { Poop } from './Poop.js';
import { Egg } from './Egg.js';
export class Chiken { 
    constructor(id, name, color, age, is_molting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.is_molting = is_molting;
    }

    toString() {
        return (
            `Chiken{\n` +
            `id---->\t\t${this.id}, \n` +
            `name---->\t\t${this.name}, \n` +
            `color-->\t\t${this.color}, \n` +
            `age---->\t\t${this.age}, \n` +
            `isMolting--->\t${this.is_molting}}`
        );
    }

    _cluck() {
        console.log(` chicken ${this.name} is clucking cluck cluck cluck`);
    }

    _eat() {
        console.log(` chicken ${this.name} is eating, grains`);
    }

    poop(amount) {
        const poop = new Poop(amount);
        console.log(` chicken ${this.name} is pooping ${poop.toString()}`);
        return poop;
    }

    lay_an_egg(size) {
        const egg = new Egg(size);
        console.log(` chicken ${this.name} is laying (${egg.size}) size egg`);
        return egg;
    }

    drink() {
        console.log(` chicken ${this.name} is drinking`);
    }

    wander() {
        console.log(` chicken ${this.name} is wandering`);
    }
}
