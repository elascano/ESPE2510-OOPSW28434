const Poop = require('./Poop');
const Egg = require('./Egg');

class Chicken {
    static nextId = 1;

    constructor(name, color, age, isMolting) {
        this.id = Chicken.nextId++;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }

    cluck() { console.log(`Chicken ${this.name} is clucking cluck cluck cluck`); }
    eat() { console.log(`Chicken ${this.name} is eating grains`); }
    drink() { console.log(`Chicken ${this.name} is drinking water`); }
    wander() { console.log(`Chicken ${this.name} is wandering`); }
    poop(amount) {
        const poop = new Poop(amount);
        console.log(`Chicken ${this.name} is pooping ${poop}`);
        return poop;
    }
    layAnEgg(size) {
        const egg = new Egg(size);
        console.log(`Chicken ${this.name} is laying a ${egg.size} size egg`);
        return egg;
    }

    doStuff() {
        const actions = ['cluck', 'eat', 'drink', 'wander', 'poop', 'layAnEgg'];
        const randomActions = Math.floor(Math.random() * 5) + 3;
        for (let i = 0; i < randomActions; i++) {
            const action = actions[Math.floor(Math.random() * actions.length)];
            switch (action) {
                case 'cluck': this.cluck(); break;
                case 'eat': this.eat(); break;
                case 'drink': this.drink(); break;
                case 'wander': this.wander(); break;
                case 'poop': this.poop(Math.floor(Math.random() * 5) + 1); break;
                case 'layAnEgg':
                    const sizes = ['S', 'M', 'L'];
                    this.layAnEgg(sizes[Math.floor(Math.random() * sizes.length)]);
                    break;
            }
        }
    }

    toString() {
        return `\nChicken{ id: ${this.id}, name: ${this.name}, color: ${this.color}, age: ${this.age}, isMolting: ${this.isMolting} }`;
    }
}

module.exports = Chicken;
