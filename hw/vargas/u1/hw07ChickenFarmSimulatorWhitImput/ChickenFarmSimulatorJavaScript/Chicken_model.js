const { Egg } = require('./Egg_model.js'); 
const { Poop } = require('./Poop_model.js'); 

/**
 * Function for select a random element of array.
 * 
 * @param {Array} array 
 */
function randomChoice(array) {
    const randomIndex = Math.floor(Math.random() * array.length);
    return array[randomIndex];
}

class Chicken {
    constructor(id, name, color, age, isMolting) {
        this._id = id;
        this._name = name;
        this._color = color;
        this._age = age;
        this._isMolting = isMolting;
        
        this.methodsChicken = [
            this.cluck, 
            this.eat, 
            this.wander, 
            this.drink, 
            this.poop, 
            this.layAnEgg
        ];
    }

    cluck() {
        console.log(`chicken ${this._name} is clucking`);
    }

    eat() {
        console.log(`chicken ${this._name} is eating`);
    }
    
    wander() {
        console.log(`chicken ${this._name} is wandering`);
    }
    
    drink() {
        console.log(`chicken ${this._name} is drinking`);
    }

    poop() {
        const poop = new Poop();
        console.log(`chicken ${this._name} is pooping ${poop.toString()}`);
        return poop;
    }

    layAnEgg() {
        const egg = new Egg();
        console.log(`chicken ${this._name} is laying an egg ${egg.toString()} size egg`);
        return egg;
    }

    doStuff() {
        const chooseMethod = randomChoice(this.methodsChicken);
        chooseMethod.call(this); 
    }
    
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
    toString() {
        return (
            `Chicken: \n` +
            `ID --> \t\t${this._id}\n` +
            `Name --> \t${this._name}\n` +
            `Color --> \t${this._color}\n` +
            `Age --> \t\t${this._age}\n` +
            `isMolting --> \t${this._isMolting}`
        );
    }
}

module.exports = {
    Chicken
};