const readline = require('readline-sync');
const Chicken = require('./Chicken');
const View = require('./ChickenFarmSimulatorview');

const view = new View();
const chickens = [];

function mainMenu() {
    let option;
    do {
        console.log('\n===== CHICKEN FARM MENU =====');
        console.log('1. Add new chicken');
        console.log('2. Show all chickens');
        console.log('0. Exit');
        option = readline.questionInt('Select an option: ');

        switch (option) {
            case 1:
                addChicken();
                break;
            case 2:
                showAllChickens();
                break;
            case 0:
                console.log('Exiting...');
                break;
            default:
                console.log('Invalid option. Try again.');
        }
    } while (option !== 0);
}

function addChicken() {
    const name = readline.question('Chicken name: ');
    const age = readline.questionInt('Age: ');
    const color = readline.question('Color: ');
    const moltingInput = readline.question('Is the chicken molting? (yes/no): ').toLowerCase();
    const molting = moltingInput === 'yes';

    const chicken = new Chicken(name, age, color, molting);
    chickens.push(chicken);
    view.showMessage('Chicken added successfully.');
}

function showAllChickens() {
    if (chickens.length === 0) {
        view.showMessage('No chickens registered.');
        return;
    }

    view.showMessage('--- Chicken List ---');
    chickens.forEach(chicken => view.showChicken(chicken));
}

// Run the main menu
mainMenu();