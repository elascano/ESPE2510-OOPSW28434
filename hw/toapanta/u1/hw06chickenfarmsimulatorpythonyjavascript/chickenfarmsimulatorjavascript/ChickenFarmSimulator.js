class Chicken {
    constructor() {
        this.id = 0;
        this.name = "";
        this.color = "";
        this.age = 0;
        this.isMolting = false;
    }

    toString() {
        return `Chicken{id=${this.id}, name=${this.name}, color=${this.color}, age=${this.age}, isMolting=${this.isMolting}}`;
    }

    setId(id) {
        this.id = id;
    }

    getId() {
        return this.id;
    }

    getName() {
        return this.name;
    }

    setName(name) {
        this.name = name;
    }

    getColor() {
        return this.color;
    }

    setColor(color) {
        this.color = color;
    }

    getAge() {
        return this.age;
    }

    setAge(age) {
        this.age = age;
    }

    getIsMolting() {
        return this.isMolting;
    }

    setIsMolting(isMolting) {
        this.isMolting = isMolting;
    }
}

// Main program
console.log("This is my chicken Farm Simulator");

const id = 1;
const name = "lucy";
const age = 2;
const isMolting = false;
const owner = "Adrian Toapanta";

// Create chicken object
let chicken = new Chicken();
chicken.setId(id);
chicken.setName(name);
chicken.setAge(age);
chicken.setIsMolting(isMolting);

console.log("The chicken is --> " + chicken.toString());
console.log("chicken id --> " + chicken.getId() + " " + chicken.getName());
console.log("Farm owner --> " + owner);