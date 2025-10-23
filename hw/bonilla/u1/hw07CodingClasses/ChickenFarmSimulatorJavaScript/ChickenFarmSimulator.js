class Poop {
    constructor(amount) {
        this.amount = amount;
    }

    toString() {
        return `Poop{amount=${this.amount}}`;
    }

    getAmount() {
        return this.amount;
    }

    setAmount(amount) {
        this.amount = amount;
    }
}

class Egg {
    constructor(size) {
        this.size = size;
    }

    toString() {
        return `Egg{size=${this.size}}`;
    }

    getSize() {
        return this.size;
    }

    setSize(size) {
        this.size = size;
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
        return `
Chicken{
    id ->        ${this.id},
    name ->      ${this.name},
    color ->     ${this.color},
    age ->       ${this.age},
    isMolting -> ${this.isMolting}
}`;
    }
}

const readline = require("readline");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function ask(question) {
    return new Promise((resolve) => rl.question(question, resolve));
}

async function main() {
    console.log("---Chicken Farm---");

    const chicken1 = new Chicken(1, "Lucy", "White and Brown", 2, false);
    const chicken2 = new Chicken(2, "Maruja", "White", 1, true);
    const chicken3 = new Chicken(3, "Carmen", "Golden", 3, false);
    const chickens = [chicken1, chicken2, chicken3];

    while (true) {
        console.log("\n1. Show the chickens' characteristics");
        console.log("2. Show the chicken's routine");
        console.log("3. Exit");

        const choice = await ask("Choose an option: ");

        if (choice === "1") {
            chickens.forEach(chicken => console.log(chicken.toString()));
        } else if (choice === "2") {
            while (true) {
                console.log("\nWhich chicken's routine do you want to see?");
                console.log("1. Lucy");
                console.log("2. Maruja");
                console.log("3. Carmen");
                const subChoice = await ask("Choose an option: ");

                if (["1", "2", "3"].includes(subChoice)) {
                    chickens[parseInt(subChoice) - 1].doStuff();
                    break;
                } else {
                    console.log("Invalid choice, please select 1, 2, or 3.");
                }
            }
        } else if (choice === "3") {
            console.log("Goodbye!");
            break;
        } else {
            console.log("Invalid choice, please select 1, 2, or 3.");
        }
    }

    rl.close();
}

main();
