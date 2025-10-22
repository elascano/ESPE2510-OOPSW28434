class Poop {
    constructor(amount) {
        this.amount = amount;
    }

    toString() {
        return `${this.amount} poop(s)`;
    }
}

class Egg {
    constructor(size) {
        this.size = size;
    }

    getSize() {
        return this.size;
    }
}

class Chicken {
    constructor(id, name, color, age, isMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }

    #cluck() {
        console.log(`chicken ${this.name} is clucking, cluck cluck cluck`);
    }

    #eat() {
        console.log(`chicken ${this.name} is eating, grains`);
    }

    doStuff() {
        this.#cluck();
        this.#eat();
        this.#cluck();
        this.poop(2);
        this.poop(3);
        this.#eat();
        this.wander();
        this.drink();
        this.layAnEgg('M');
        this.layAnEgg('L');
    }

    poop(amount) {
        const poop = new Poop(amount);
        console.log(`chicken ${this.name} is pooping a ${poop}`);
        return poop;
    }

    layAnEgg(size) {
        const egg = new Egg(size);
        console.log(`chicken ${this.name} is laying a ${egg.getSize()} size egg`);
        return egg;
    }

    wander() {
        console.log(`chicken ${this.name} is wandering`);
    }

    drink() {
        console.log(`chicken ${this.name} is drinking`);
    }

    toString() {
        return `Chicken {
    id ->        ${this.id},
    name ->      ${this.name},
    color ->     ${this.color},
    age ->       ${this.age},
    isMolting -> ${this.isMolting}
    }`;
    }

    getId() {
        return this.id;
    }

    setId(id) {
        this.id = id;
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

const chicken = new Chicken(1, "Lucy", "white", 2, false);
console.log(chicken.toString());
chicken.doStuff();
