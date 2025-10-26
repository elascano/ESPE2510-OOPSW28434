import { Chicken } from './chicken.js';
import { ChickenCoop } from './chickencoop.js';
import readline from 'readline';

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function question(prompt) {
    return new Promise((resolve) => {
        rl.question(prompt, resolve);
    });
}

async function main() {
    console.log("Welcome to the Chicken Farm Simulator\n");
    
    const coop1 = new ChickenCoop("Main Coop");
    const coop2 = new ChickenCoop("Secondary Coop");
    const coops = [coop1, coop2];
    
    console.log("=== Creating 10 Chickens ===");
    const chickens = createMultipleChickens(10);
    
    assignChickensToCoops(chickens, coops);
    
    await showMenu(coops);
    
    rl.close();
}

function createMultipleChickens(amount) {
    const chickens = [];
    const names = ["Lucy", "Maruja", "Rosita", "Clara", "Pepe", "Lola", "Pepa", "Blanca", "Negra", "Amarilla"];
    const colors = ["White", "Brown", "Black", "Yellow", "Gray", "Red", "Orange"];
    
    for (let i = 1; i <= amount; i++) {
        const name = names[Math.floor(Math.random() * names.length)];
        const color = colors[Math.floor(Math.random() * colors.length)];
        const age = Math.floor(Math.random() * 5) + 1;
        const isMolting = Math.random() < 0.5;
        
        const chicken = new Chicken(i, name, color, age, isMolting);
        chickens.push(chicken);
        console.log(`Created Chicken ${i}: ${name} (${color}, ${age} years, molting: ${isMolting})`);
    }
    
    return chickens;
}

function assignChickensToCoops(chickens, coops) {
    console.log("\n=== Assigning Chickens to Coops ===");
    chickens.forEach((chicken, index) => {
        if (index < 6) {
            coops[0].addChicken(chicken);
        } else {
            coops[1].addChicken(chicken);
        }
    });
}

function displayAllCoops(coops) {
    console.log("\n--- All Chicken Coops ---");
    coops.forEach((coop, index) => {
        console.log(`${index + 1}. ${coop.toString()}`);
    });
}

function displayAllChickens(coops) {
    console.log("\n--- All Chickens in Farm ---");
    const allChickens = [];
    coops.forEach(coop => {
        allChickens.push(...coop.getAllChickens());
    });
    
    if (allChickens.length === 0) {
        console.log("No chickens in the farm.");
    } else {
        allChickens.forEach(chicken => {
            console.log(`ID: ${chicken.id}, Name: ${chicken.name}, Color: ${chicken.color}`);
        });
    }
}

function findChickenById(chickenId, coops) {
    for (const coop of coops) {
        const chicken = coop.getChicken(chickenId);
        if (chicken) {
            return chicken;
        }
    }
    return null;
}

async function moveChickenBetweenCoops(coops) {
    displayAllCoops(coops);
    
    try {
        const sourceCoopNum = parseInt(await question("Enter source coop number: ")) - 1;
        if (sourceCoopNum < 0 || sourceCoopNum >= coops.length) {
            console.log("Invalid source coop number.");
            return;
        }
        
        const sourceCoop = coops[sourceCoopNum];
        if (sourceCoop.isEmpty()) {
            console.log("Source coop is empty.");
            return;
        }
        
        sourceCoop.displayChickens();
        const chickenId = parseInt(await question("Enter chicken ID to move: "));
        const chicken = sourceCoop.getChicken(chickenId);
        
        if (!chicken) {
            console.log(`Chicken with ID ${chickenId} not found in source coop.`);
            return;
        }
        
        displayAllCoops(coops);
        const targetCoopNum = parseInt(await question("Enter target coop number: ")) - 1;
        if (targetCoopNum < 0 || targetCoopNum >= coops.length || targetCoopNum === sourceCoopNum) {
            console.log("Invalid target coop number.");
            return;
        }
        
        sourceCoop.removeChicken(chickenId);
        coops[targetCoopNum].addChicken(chicken);
        console.log(`Chicken '${chicken.name}' moved from ${sourceCoop.toString()} to ${coops[targetCoopNum].toString()}`);
        
    } catch (error) {
        console.log("Please enter valid numbers.");
    }
}

async function addNewChicken(coops) {
    displayAllCoops(coops);
    
    try {
        const coopChoice = parseInt(await question("Enter coop number to add chicken: ")) - 1;
        if (coopChoice < 0 || coopChoice >= coops.length) {
            console.log("Invalid coop number.");
            return;
        }
        
        const allChickens = [];
        coops.forEach(coop => {
            allChickens.push(...coop.getAllChickens());
        });
        
        const nextId = allChickens.length > 0 ? 
            Math.max(...allChickens.map(chicken => chicken.id)) + 1 : 1;
        
        const chicken = await createChickenFromInput(nextId);
        coops[coopChoice].addChicken(chicken);
        
    } catch (error) {
        console.log("Please enter a valid number.");
    }
}

async function removeChickenFromCoop(coops) {
    displayAllCoops(coops);
    
    try {
        const coopChoice = parseInt(await question("Enter coop number: ")) - 1;
        if (coopChoice < 0 || coopChoice >= coops.length) {
            console.log("Invalid coop number.");
            return;
        }
        
        const coop = coops[coopChoice];
        if (coop.isEmpty()) {
            console.log("Coop is empty.");
            return;
        }
        
        coop.displayChickens();
        const chickenId = parseInt(await question("Enter chicken ID to remove: "));
        coop.removeChicken(chickenId);
        
    } catch (error) {
        console.log("Please enter valid numbers.");
    }
}

async function createChickenFromInput(chickenId) {
    console.log(`\n--- Creating Chicken ${chickenId} ---`);
    
    let name;
    while (true) {
        name = (await question(`Enter name for chicken ${chickenId}: `)).trim();
        if (name.replace(/ /g, "").match(/^[a-zA-Z]+$/)) {
            break;
        } else {
            console.log("Please enter only letters (no numbers or special characters).");
        }
    }
    
    const color = await question(`Enter color for chicken ${chickenId}: `);
    
    let age;
    while (true) {
        const ageInput = await question(`Enter age for chicken ${chickenId}: `);
        age = parseInt(ageInput);
        if (!isNaN(age)) {
            break;
        } else {
            console.log("Please enter a valid integer for age.");
        }
    }
    
    let isMolting;
    while (true) {
        const moltingInput = (await question(`Is chicken ${chickenId} molting? (yes/no): `)).toLowerCase();
        if (['yes', 'y', 'true', '1'].includes(moltingInput)) {
            isMolting = true;
            break;
        } else if (['no', 'n', 'false', '0'].includes(moltingInput)) {
            isMolting = false;
            break;
        } else {
            console.log("Please enter 'yes' or 'no'.");
        }
    }
    
    return new Chicken(chickenId, name, color, age, isMolting);
}

async function showMenu(coops) {
    while (true) {
        console.log("\n--- Chicken Farm Menu ---");
        console.log("1. View all coops");
        console.log("2. View chickens in a specific coop");
        console.log("3. Make a chicken do its routine");
        console.log("4. Move chicken between coops");
        console.log("5. Add new chicken");
        console.log("6. Remove chicken");
        console.log("7. Exit");
        
        const option = await question("Choose an option (1-7): ");
        
        switch (option) {
            case "1":
                displayAllCoops(coops);
                break;
                
            case "2":
                displayAllCoops(coops);
                try {
                    const coopChoice = parseInt(await question("Enter coop number: ")) - 1;
                    if (coopChoice >= 0 && coopChoice < coops.length) {
                        coops[coopChoice].displayChickens();
                    } else {
                        console.log("Invalid coop number.");
                    }
                } catch (error) {
                    console.log("Please enter a valid number.");
                }
                break;
                
            case "3":
                displayAllChickens(coops);
                try {
                    const chickenId = parseInt(await question("Enter chicken ID to perform routine: "));
                    const chicken = findChickenById(chickenId, coops);
                    if (chicken) {
                        chicken.doStuff();
                    } else {
                        console.log(`Chicken with ID ${chickenId} not found.`);
                    }
                } catch (error) {
                    console.log("Please enter a valid ID number.");
                }
                break;
                
            case "4":
                await moveChickenBetweenCoops(coops);
                break;
                
            case "5":
                await addNewChicken(coops);
                break;
                
            case "6":
                await removeChickenFromCoop(coops);
                break;
                
            case "7":
                console.log("Exiting...");
                return;
                
            default:
                console.log("Please choose a valid option (1-7).");
        }
    }
}

main().catch(console.error);