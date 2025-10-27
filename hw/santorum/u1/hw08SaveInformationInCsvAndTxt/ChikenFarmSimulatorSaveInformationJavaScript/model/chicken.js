const egg = require('./egg');
const poop = require('./poop');

class chicken {
    constructor(id, name, color, age, isMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }

    cluck() {
        console.log(`${this.name} is clucking.`);
    }

    eat() {
        console.log(`${this.name} is eating.`);
    }

    wander() {
        console.log(`${this.name} is wandering.`);
    }

    drink() {
        console.log(`${this.name} is drinking.`);
    }

    poopChicken(amount) {
        const p = new poop(amount);
        console.log(`${this.name} is pooping ${amount} times.`);
        return p;
    }

    layAnEgg(size) {
        const e = new egg(size);
        console.log(`${this.name} is laying a ${size} size egg.`);
        return e;
    }

    doStuff() {
        this.cluck();
        this.eat();
        this.cluck();
        this.poopChicken(2);
        this.poopChicken(3);
        this.eat();
        this.wander();
        this.drink();
        this.layAnEgg('M');
        this.layAnEgg('L');
    }

    toString() {
        return `chicken(${this.id}, ${this.name}, ${this.color}, ${this.age}, Molting=${this.isMolting})`;
    }
}

module.exports = chicken;

