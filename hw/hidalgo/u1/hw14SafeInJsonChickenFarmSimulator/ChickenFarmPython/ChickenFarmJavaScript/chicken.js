const Egg = require('./Egg');
const Poop = require('./Poop');

class Chicken {
    constructor(id, name, color, age, isMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting; // Propiedad directa
    }
    
    doStuff() {
        this.cluck();
        this.eat();
        this.wander();
        this.drink();
        
        const eggSizes = ['S', 'M', 'L'];
        const randomSize = eggSizes[Math.floor(Math.random() * eggSizes.length)];
        this.layAnEgg(randomSize);
        
        const randomAmount = Math.floor(Math.random() * 3) + 1;
        this.poop(randomAmount);
    }
    
    cluck() {
        console.log(`Chicken ${this.name} is clucking: cluck, cluck, cluck`);
    }
    
    eat() {
        console.log(`Chicken ${this.name} is eating grains`);
    }
    
    wander() {
        console.log(`Chicken ${this.name} is wandering around`);
    }
    
    drink() {
        console.log(`Chicken ${this.name} is drinking water`);
    }
    
    layAnEgg(size) {
        const egg = new Egg(size);
        console.log(`Chicken ${this.name} laid a ${size} size egg!`);
        return egg;
    }
    
    poop(amount) {
        const poop = new Poop(amount);
        console.log(`Chicken ${this.name} pooped ${amount} times`);
        return poop;
    }
   
    // Getters
    getId() { return this.id; }
    getName() { return this.name; }
    getColor() { return this.color; }
    getAge() { return this.age; }
    getIsMolting() { return this.isMolting; } // Getter opcional
    
    // Setters
    setName(name) { this.name = name; }
    setColor(color) { this.color = color; }
    setAge(age) { this.age = age; }
    setMolting(isMolting) { this.isMolting = isMolting; }
    
    toString() {
        return `Chicken{id=${this.id}, name='${this.name}', color='${this.color}', age=${this.age}, isMolting=${this.isMolting}}`;
    }
}

module.exports = Chicken;