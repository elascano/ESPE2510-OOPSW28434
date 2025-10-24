export class Chicken {
    constructor(id, name, color, age, isMolting) {
        this._id = id;
        this._name = name;
        this._color = color;
        this._age = age;
        this._isMolting = isMolting;
    }

    #cluck() {
        console.log(`Chicken ${this._name} is clucking, cluck cluck cluck`);
    }

    #eat() {
        console.log(`Chicken ${this._name} is eating grains`);
    }

    doStuff() {
        this.#cluck();
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
        const poop = { 
            amount: amount,
            toString: function() { return `Poop{amount = ${this.amount}}`; }
        };
        console.log(`Chicken ${this._name} is pooping a ${poop}`);
        return poop;
    }

    layAnEgg(size) {
        const egg = { 
            size: size,
            toString: function() { return `Egg{size = ${this.size}}`; }
        };
        console.log(`Chicken ${this.name} is laying a ${egg.size}-sized egg`);
        return egg;
    }

    wonder() {
        console.log(`Chicken ${this.name} is wandering`);
    }

    drink() {
        console.log(`Chicken ${this.name} is drinking`);
    }

    toString() {
        return `\n${this._name}:{\n` +
               `id \t\t->\t${this._id}\n` +
               `name \t\t->\t${this._name}\n` +
               `color \t\t->\t${this._color}\n` +
               `age \t\t->\t${this._age}\n` +
               `isMolting \t->\t${this._isMolting}}`;
    }

    get id() {
        return this._id;
    }

    get name() {
        return this._name;
    }

    get color() {
        return this._color;
    }

    get age() {
        return this._age;
    }

    get isMolting() {
        return this._isMolting;
    }

    set id(id) {
        this._id = id;
    }

    set name(name) {
        this._name = name;
    }

    set color(color) {
        this._color = color;
    }

    set age(age) {
        this._age = age;
    }

    set isMolting(isMolting) {
        this._isMolting = isMolting;
    }
}