const fs = require('fs');
const readline = require('readline');

const Chicken = require('../ec.espe.edu.chickenFarmSimulator.model/chicken');
const ChickenCoop = require('../ec.espe.edu.chickenFarmSimulator.model/chickenCoops');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const myChickenCoop = new ChickenCoop(1);
let chickenIdCounter = 1; 
let chickensToCreate = [];

function showMenu() {
    console.log('\n======================================');
    console.log('         CHICKEN COOP MENU');
    console.log('======================================');
    console.log('1. Add Chickens');
    console.log('2. Show Chickens');
    console.log('3. Exit');
    console.log('======================================');
    rl.question('Select an option (1, 2, or 3): ', handleUserInput);
}

function handleUserInput(choice) {
    switch (choice.trim()) {
        case '1':
            requestNumberOfChickens();
            break;
        case '2':
            myChickenCoop.showChickens();
            showMenu();
            break;
        case '3':
            console.log('Exiting the program. Goodbye!');
            rl.close();
            break;
        default:
            console.log('Invalid option. Please try again.');
            showMenu();
            break;
    }
}

function requestNumberOfChickens() {
    rl.question('How many chickens do you want to add?: ', (count) => {
        const numChickens = parseInt(count.trim());

        if (isNaN(numChickens) || numChickens <= 0) {
            console.log('Please enter a valid positive integer.');
            requestNumberOfChickens();
            return;
        }

        chickensToCreate = [];
        requestChickenDetails(numChickens, 1);
    });
}

function requestChickenDetails(totalCount, currentNumber) {
    console.log(`\n--- Enter details for Chicken ${currentNumber} of ${totalCount} (ID: ${chickenIdCounter}) ---`);
    let details = { 
        id: chickenIdCounter++,
        chickenCoopNumber: myChickenCoop.chickenCoopNumber
    };

    rl.question('Name: ', (name) => {
        details.name = name.trim();
        rl.question('Color: ', (color) => {
            details.color = color.trim();
            rl.question('Age (years): ', (age) => {
                details.age = parseInt(age.trim());
                if (isNaN(details.age) || details.age < 0) {
                    console.log('Invalid age. Please try again with a number.');
                    chickenIdCounter--; 
                    requestChickenDetails(totalCount, currentNumber);
                    return;
                }
                rl.question('Is it molting (y/n)?: ', (isMoltingInput) => {
                    const isMolting = isMoltingInput.trim().toLowerCase() === 'y';
                    details.isMolting = isMolting;

                    chickensToCreate.push(details);

                    if (currentNumber < totalCount) {
                        requestChickenDetails(totalCount, currentNumber + 1);
                    } else {
                        processChickenCreation();
                    }
                });
            });
        });
    });
}

function processChickenCreation() {
    let newlyAddedChickens = [];

    console.log('\n--- Creating and adding chickens to the coop... ---');
    for (const data of chickensToCreate) {
        const newChicken = new Chicken(
            data.id,
            data.name,
            data.color,
            data.age,
            data.isMolting
        );
        myChickenCoop.addChicken(newChicken);
        newlyAddedChickens.push(newChicken);
        console.log(`✅ Chicken ${data.name} (ID: ${data.id}) added.`);
    }

    createAndShowJsonFile(chickensToCreate);

    rl.question('\nDo you want to see the actions (doStuff) of the new chickens? (y/n): ', (answer) => {
        if (answer.trim().toLowerCase() === 'y') {
            for (const chicken of newlyAddedChickens) {
                chicken.doStuff();
            }
        } else {
            console.log('doStuff() will not be called.');
        }

        showMenu();
    });
}

function createAndShowJsonFile(data) {
    const filename = 'chickens_data.json';

    try {
        const jsonContent = JSON.stringify(data, null, 2);
        fs.writeFileSync(filename, jsonContent);

        console.log('\n======================================');
        console.log(`JSON CREATED AND STORED IN: ${filename}`);
        console.log('======================================');
        console.log(jsonContent);
        console.log('======================================\n');
        
    } catch (error) {
        console.error(`❌ ERROR creating the JSON file: ${error.message}`);
    }
}

showMenu();