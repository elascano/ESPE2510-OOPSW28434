const Chicken = require('./Chicken');
const { makeChickenPoop } = require('./Poop');
const { layEgg } = require('./Egg');
const readline = require('readline');

class ChickenFarmSimulator {
    constructor() {
        this.chickens = [];
        this.rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
        this.initializeChickens();
    }

    initializeChickens() {
        this.chickens.push(new Chicken(1, "Lucy", "White and Brown", false, 2));
        this.chickens.push(new Chicken(2, "Maruja", "white", true, 1));
    }

    question(prompt) {
        return new Promise((resolve) => {
            this.rl.question(prompt, resolve);
        });
    }

    async addNewChicken() {
        console.log("\n---- Add New Chicken ----");
        const nextId = this.chickens.length === 0 ? 1 : this.chickens[this.chickens.length - 1].id + 1;

        const name = await this.question("Chicken name: ");
        const color = await this.question("Color: ");

        let age;
        while (true) {
            const ageInput = await this.question("Age (years): ");
            age = parseInt(ageInput);
            if (!isNaN(age) && age >= 0) break;
            console.log("Error: Please enter a valid positive number for age");
        }

        let isMolting;
        while (true) {
            const moltingInput = await this.question("isMolting (true/false): ");
            if (moltingInput.toLowerCase() === 'true' || moltingInput.toLowerCase() === 'false') {
                isMolting = moltingInput.toLowerCase() === 'true';
                break;
            } else {
                console.log("Please enter 'true' or 'false'");
            }
        }

        const newChicken = new Chicken(nextId, name, color, isMolting, age);
        this.chickens.push(newChicken);

        console.log("Chicken added successfully!");
        console.log(`Assigned ID: ${nextId}`);
    }

    showAllChickens() {
        console.log("\n--- All Registered Chickens ---");
        if (this.chickens.length === 0) {
            console.log("No chickens registered.");
            return;
        }

        for (const chicken of this.chickens) {
            console.log(`the chicken is ---> Chicken{`);
            console.log(`id -> ${chicken.id}`);
            console.log(`name -> ${chicken.name}`);
            console.log(`color -> ${chicken.color}`);
            console.log(`age -> ${chicken.age}`);
            console.log(`isMolting -> ${chicken.isMolting}`);
            console.log(`}`);
        }

        this.makeAllChickensAct();
    }

    wander(chicken) {
        console.log(`chicken ${chicken.name} is wandering`);
    }

    drink(chicken) {
        console.log(`chicken ${chicken.name} is drinking`);
    }

    makeAllChickensAct() {
        console.log("\n--- Chicken Actions ---");
        for (const chicken of this.chickens) {
            console.log(`chicken id --> ${chicken.id} ${chicken.name}`);
            chicken.cluck();
            chicken.eat();
            chicken.cluck();
            makeChickenPoop(chicken, 2);
            makeChickenPoop(chicken, 3);
            chicken.eat();
            this.wander(chicken);
            this.drink(chicken);
            layEgg(chicken, "M");
            layEgg(chicken, "L");
        }
    }

    async run() {
        console.log("Chicken Farm Simulator - by Maryuri");

        while (true) {
            console.log("\n----- MAIN MENU -----");
            console.log("1. Register chicken");
            console.log("2. View chickens");
            console.log("3. Exit");

            const option = await this.question("Choose an option (1-3): ");
            const optionNum = parseInt(option);

            if (optionNum === 1) {
                await this.addNewChicken();
            } else if (optionNum === 2) {
                this.showAllChickens();
            } else if (optionNum === 3) {
                break;
            } else {
                console.log("Invalid option, please try again.");
            }
        }

        this.makeAllChickensAct();
        console.log("\nBUILD SUCCESSFUL");
        this.rl.close();
    }
}

async function main() {
    try {
        const farm = new ChickenFarmSimulator();
        await farm.run();
    } catch (error) {
        console.log(`\nAn error occurred: ${error}`);
    }
}

main();