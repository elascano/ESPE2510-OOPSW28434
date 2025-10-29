import { Chicken } from "../model/Chicken.js";
import { ChickenCoop } from "../model/ChickenCoop.js";
import fs from "fs";
import readline from "readline";
import path from "path";
import { fileURLToPath } from "url";

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function ask(question) {
    return new Promise(resolve => rl.question(question, answer => resolve(answer)));
}

function loadFarmData() {
    try {
        const packagePath = path.join(__dirname, "..", "package.json");
        const packageJson = JSON.parse(fs.readFileSync(packagePath, "utf8"));
        return packageJson.chickenFarmData;
    } catch (error) {
        return {
            chickens: [],
            coops: [],
            lastUpdate: ""
        };
    }
}

function saveFarmData(farmData) {
    try {
        const packagePath = path.join(__dirname, "..", "package.json");
        const packageJson = JSON.parse(fs.readFileSync(packagePath, "utf8"));
        packageJson.chickenFarmData = farmData;
        fs.writeFileSync(packagePath, JSON.stringify(packageJson, null, 2), "utf8");
    } catch (error) {
        console.error("Error:", error);
    }
}

function showChickens(farmData) {
    console.log("\n LIST OF CHICKENS ");
    if (farmData.chickens.length === 0) {
        console.log("There are no registered hens.");
        return;
    }
    
    farmData.chickens.forEach((chicken, index) => {
        console.log(`${index + 1}.  ${chicken.name}`);
        console.log(`   ID: ${chicken.id}`);
        console.log(`   age: ${chicken.age} `);
        console.log(`   ChickenCoop: ${chicken.coopId}`);
        console.log("");
    });
}

function showChickenActions(farmData) {
    console.log("\nCHICKEN ACTIONS");
    if (farmData.chickens.length === 0) {
        console.log("There are no chickens to show actions.");
        return;
    }
    
    farmData.chickens.forEach((chicken, index) => {
        console.log(` ${chicken.name} (${chicken.age} months) is:`);
        
        const actions = [
            " clucking"
        ];
        
        const randomAction = actions[Math.floor(Math.random() * actions.length)];
        console.log(`   ${randomAction}`);
        console.log("");
    });
}

async function main() {
    console.log(" Farm Simulation by Kevin Chalan \n");

    let farmData = {
        chickens: [],
        coops: [],
        lastUpdate: ""
    };

    saveFarmData(farmData);
    console.log("Simulation\n");

    const numCoops = parseInt(await ask("¿How many chicken coops do you want to create?? "));

    for (let coopIndex = 0; coopIndex < numCoops; coopIndex++) {
        console.log(`\nChickenCoops  #${coopIndex + 1}`);
        const coopId = parseInt(await ask("ID ChickenCoop: "));
        
        const numChickens = parseInt(await ask(`¿How many hens do you want in the henhouse? ${coopId}? `));

        for (let i = 0; i < numChickens; i++) {
            console.log(`\nChicken #${i + 1} in chickencoop ${coopId}`);

            const id = parseInt(await ask("ID chicken: "));
            const name = await ask("Name: ");
            const age = parseInt(await ask("Age: "));

            const newChicken = {
                name: name,
                id: id,
                age: age,
                coopId: coopId
            };

            farmData.chickens.push(newChicken);
            
            if (!farmData.coops.some(coop => coop.id === coopId)) {
                farmData.coops.push({
                    id: coopId,
                    chickenCount: 1
                });
            } else {
                const coop = farmData.coops.find(coop => coop.id === coopId);
                coop.chickenCount++;
            }

            farmData.lastUpdate = new Date().toLocaleString();
            saveFarmData(farmData);
            
            console.log("Chicken saved in .json");
        }
    }

    console.log(`\nSIMULATION!`);
    console.log(`tOTAL cHICKEN: ${farmData.chickens.length}`);
    console.log(`Total chickencops: ${farmData.coops.length}`);
    console.log(`Saving in package.json`);


    const seeChickens = await ask("\n¿Do you want to see the list of chickens? (yes/no): ");
    if (seeChickens.toLowerCase() === "yes") {
        showChickens(farmData);
    }


    const seeActions = await ask("\n¿Do you want to see what the chickens are doing? (yes/no): ");
    if (seeActions.toLowerCase() === "yes") {
        showChickenActions(farmData);
    }

    console.log("\ngoodbye");
    rl.close();
}

main().catch(console.error);