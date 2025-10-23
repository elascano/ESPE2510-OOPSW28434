const readline = require('readline'); 
const { Chicken } = require('./Chicken_model.js'); 
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

/**
 * Function to ask a question and get the user's response.
 * @param {string} query - the question to display in the console.
 * @returns {Promise<string>} The answer from the user.
 */
function askQuestion(query) {
    return new Promise(resolve => rl.question(query, ans => {
        resolve(ans);
    }));
}

async function mainSimulator() {
    console.log("Welcome to the Chicken Farm Simulator by Vargas Cesar \n");

    let id = 1;
    let name = "Lucy";
    let color = "White and Brown";
    let age = 2;
    let isMolting = false;

    const chicken = new Chicken(id, name, color, age, isMolting);
    chicken.doStuff();
    do{
    console.log("\nNow is your moment to Register a new chicken");

    const idInput = await askQuestion("Enter chicken ID: ");
    const newId = parseInt(idInput);
    const newName = await askQuestion("Enter chicken name: ");
    const newColor = await askQuestion("Enter chicken color: ");
    let newAgeInput;
    do {
    const ageInput = await askQuestion("Enter chicken age: ");
    if (ageInput < 1 || ageInput > 10) {
        console.log("Please enter a valid age between 1 and 10.");
    }
    newAgeInput = parseInt(ageInput);
} while (newAgeInput < 1 || newAgeInput > 10);
    const newAge = newAgeInput;

    const moltingInput = (await askQuestion("Is the chicken molting? (yes/no): ")).toLowerCase().trim();
    const newIsMolting = moltingInput === "yes";

    const chicken2 = new Chicken(newId, newName, newColor, newAge, newIsMolting);


    console.log(`\nYou have registered a new chicken:\n${chicken2.toString()}\n`); 

    console.log("Watch the chicken do its actions:");
    chicken2.doStuff();

    let userOption;
    do {
        userOption = (await askQuestion("Do you want to see the chicken do its actions again? (yes/no): ")).toLowerCase().trim();
        
        if (userOption === "yes") {
            chicken2.doStuff();
        }
    } while (userOption === "yes");
 }while((userOption = await askQuestion("Do you want to register another chicken? (yes/no): ")).toLowerCase().trim() === "yes");

    console.log("Ending simulation");
    
    rl.close();
}

mainSimulator();