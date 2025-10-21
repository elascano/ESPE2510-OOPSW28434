const Poop = require('./Poop');
const Egg = require('./Egg');

class Chiken {
    constructor(id, name, color, age, isMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }

    toString() {
        return `\nChiken{
id---->\t${this.id},
name---->\t${this.name},
color-->\t${this.color},
age---->\t${this.age},
isMolting--->${this.isMolting}}`;
    }

    // Métodos privados (convención _)
    _cluck() {
        console.log(`Chicken ${this.name} is clucking cluck cluck cluck`);
    }

    _eat() {
        console.log(`Chicken ${this.name} is eating, grains`);
    }

    poop(amount) {
        const poop = new Poop(amount);
        console.log(`Chicken ${this.name} is pooping ${poop.toString()}`);
        return poop;
    }

    layAnEgg(size) {
        const egg = new Egg(size);
        console.log(`Chicken ${this.name} is laying ${egg.size} size egg`);
        return egg;
    }

    drink() {
        console.log(`Chicken ${this.name} is drinking`);
    }

    wander() {
        console.log(`Chicken ${this.name} is wandering`);
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
}

module.exports = Chiken;
