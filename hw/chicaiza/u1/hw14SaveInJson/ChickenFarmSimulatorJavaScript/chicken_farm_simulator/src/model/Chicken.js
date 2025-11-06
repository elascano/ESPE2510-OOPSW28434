import { Egg } from './Egg.js';

export class Chicken {
    constructor(id, name, color, age, isMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }
    
    toDict() {
        return {
            id: this.id,
            name: this.name,
            color: this.color,
            age: this.age,
            isMolting: this.isMolting
        };
    }
    
    static fromDict(data) {
        return new Chicken(
            data.id,
            data.name,
            data.color,
            data.age,
            data.isMolting
        );
    }
    
    doStuff(forTime) {
        console.log(`Chicken ${this.name} is doing stuff for ${forTime} minutes`);
    }
    
    cluck() {
        console.log(`Chicken ${this.name} is clucking: Cluck cluck cluck!`);
    }
    
    wander() {
        console.log(`Chicken ${this.name} is wandering around...`);
    }
    
    eat() {
        console.log(`Chicken ${this.name} is eating grains`);
    }
    
    drink() {
        console.log(`Chicken ${this.name} is drinking water`);
    }
    
    poop() {
        console.log(`Chicken ${this.name} is pooping`);
        return "Poop";
    }
    
    layAnEgg() {
        console.log(`Chicken ${this.name} laid an egg!`);
        return new Egg(Date.now());
    }
}