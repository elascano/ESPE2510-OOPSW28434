const readline = require("readline").createInterface({
    input: process.stdin,
    output: process.stdout
});

class Chicken {
    constructor(id, name, color, age, isMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }

    toString() {
        return `Chicken(id=${this.id}, name=${this.name}, color=${this.color}, age=${this.age}, isMolting=${this.isMolting})`;
    }

    doStuff() {
        console.log(`Chicken ${this.name} is clucking.`);
        console.log(`Chicken ${this.name} is eating.`);
        console.log(`Chicken ${this.name} is pooping 2 poops.`);
        console.log(`Chicken ${this.name} is pooping 3 poops.`);
        console.log(`Chicken ${this.name} is eating.`);
        console.log(`Chicken ${this.name} is wandering.`);
        console.log(`Chicken ${this.name} is drinking.`);
        console.log(`Chicken ${this.name} is laying an M size egg.`);
        console.log();
    }
}

async function ask(question) {
    return new Promise(resolve => readline.question(question, answer => resolve(answer)));
}

async function main() {
    console.log("=== Chicken Farm Simulator ===");

    // Chicken 1
    const chicken1 = new Chicken(1, "Lucy", "White and brown", 2, false);

    // Chicken 2
    console.log(" - - - Enter data for chicken 2 - - -");
    const id2 = parseInt(await ask("ID: "));
    const name2 = await ask("Name: ");
    const color2 = await ask("Color: ");
    const age2 = parseInt(await ask("Age: "));
    const isMolting2 = (await ask("Is the chicken molting? (yes/no): ")).toLowerCase() === "yes";
    const chicken2 = new Chicken(id2, name2, color2, age2, isMolting2);

    // Chicken 3
    console.log("\n - - - Enter data for chicken 3 - - -");
    const id3 = parseInt(await ask("ID: "));
    const name3 = await ask("Name: ");
    const color3 = await ask("Color: ");
    const age3 = parseInt(await ask("Age: "));
    const isMolting3 = (await ask("Is the chicken molting? (yes/no): ")).toLowerCase() === "yes";
    const chicken3 = new Chicken(id3, name3, color3, age3, isMolting3);

    // Display chickens
    console.log("\n / / Chickens data / /");
    console.log(chicken1.toString());
    console.log(chicken2.toString());
    console.log(chicken3.toString());

    // Do stuff
    console.log("\nAll chickens are doing stuff...");
    chicken1.doStuff();
    chicken2.doStuff();
    chicken3.doStuff();

    readline.close();
}

main();
