const Egg = require('./egg');
const Poop = require('./poop');

class Chicken {
    constructor(id, name, color, age, isMolting) {
        this._id = id;
        this._name = name;
        this._color = color;
        this._age = age;
        this._isMolting = isMolting;
    }

    #clock() {
        console.log(`Chicken ${this._name} is clucking, cluck cluck cluck`);
    }

    #eat() {
        console.log(`Chicken ${this._name} is eating granis`);
    }

    doStuff() {
        this.#clock();
        this.#eat();
        const randomPoopAmount = Math.floor(Math.random() * 5) + 1;
        this.poop(randomPoopAmount);
        this.wonder();
        this.drink();
        const eggSizes = ['S', 'M', 'L'];
        const randomEggSize = eggSizes[Math.floor(Math.random() * eggSizes.length)];
        this.layAnEgg(randomEggSize);
    }

    poop(amount) {
        const poop = new Poop(amount);
        console.log(`Chicken ${this._name} is pooping a ${poop}`);
        return poop;
    }

    layAnEgg(size) {
        const egg = new Egg(size);
        console.log(`Chicken ${this._name} is laying a ${egg.size}-sized egg`);
        return egg;
    }

    wonder() {
        console.log(`Chicken ${this._name} is wandering`);
    }

    drink() {
        console.log(`Chicken ${this._name} is drinking`);
    }

    toString() {
        return `\n${this._name}:{\n` +
               `id \t\t->\t${this._id}\n` +
               `name \t\t->\t${this._name}\n` +
               `color \t\t->\t${this._color}\n` +
               `age \t\t->\t${this._age}\n` +
               `isMolting \t->\t${this._isMolting}}`;
    }

    // Getters and setters
    get id() {
        return this._id;
    }

    set id(id) {
        this._id = id;
    }

    get name() {
        return this._name;
    }

    set name(name) {
        this._name = name;
    }

    get color() {
        return this._color;
    }

    set color(color) {
        this._color = color;
    }

    get age() {
        return this._age;
    }

    set age(age) {
        this._age = age;
    }

    get isMolting() {
        return this._isMolting;
    }

    set isMolting(isMolting) {
        this._isMolting = isMolting;
    }
}

module.exports = Chicken;