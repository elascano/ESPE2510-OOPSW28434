import { Egg } from './Egg_model.js';
import { Poop } from './Poop_model.js';
export class Chicken {
    constructor(id, name, color, age, isMolting) {
        this._id = id;
        this._name = name;
        this._color = color;
        this._age = age;
        this._isMolting = isMolting;
    }

    _cluck() {
        console.log(` chicken ${this._name} is clucking, cluck cluck cluck`);
    }

    _eat() {
        console.log(` chicken ${this._name} is eating, grains`);
    }

    wander() {
        console.log(`chicken ${this._name} is wandering`);
    }

    drink() {
        console.log(`chicken ${this._name} is drinking`);
    }

    poop(amount) {
        let poopInstance = new Poop(amount); 
        console.log(`chicken ${this._name} is pooping a ${poopInstance}`);
        return poopInstance;
    }

    layAnEgg(size) {
        let egg = new Egg(size);
        console.log(`chicken ${this._name} is laying a ${egg.getSize()} size egg`);
        return egg;
    }

    doStuff() {
        this._cluck();
        this._eat();
        this._cluck();
        this.poop(2);
        this.poop(3);
        this._eat();
        this.wander();
        this.drink();
        this.layAnEgg('M');
        this.layAnEgg('L');
    }

    getId() { return this._id; }
    getName() { return this._name; }
    getColor() { return this._color; }
    getAge() { return this._age; }
    isMolting() { return this._isMolting; } 

    toString() {
        return `\nChicken{ id -> \t\t${this._id}, name -> \t${this._name}, color -> \t${this._color}, age -> \t\t${this._age}, isMolting -> \t${this._isMolting}}`;
    }
}