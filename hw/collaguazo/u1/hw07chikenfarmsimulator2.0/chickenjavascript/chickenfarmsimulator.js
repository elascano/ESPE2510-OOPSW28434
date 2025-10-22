const  Chicken  = require('./Chicken.js');

const readline = require('readline');

console.log("This is my chicken Farm Simulator");

const owner = "Pablo Collaguazo"; 
let id = 1;
const name = "Lucy";
const color = "White and Brown";
let age = 2;
let isMolting = false;

let chicken = new Chicken(id, name, color, age, isMolting);

console.log(For example, the chicken can do the following actions:);
console.log(The chicken is: ${chicken.toString()});
chicken.doStuff();

console.log("\nNow is your turn. Register a chicken");
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

rl.question("Enter chicken name: ", (name) => {
    rl.question("Enter chicken color: ", (color) => {
        rl.question("Enter chicken age: ", (age) => {
            rl.question("Is the chicken molting? (true/false): ", (isMolting) => {
                rl.question("Enter chicken ID: ", (id) => {
                    let chicken2 = new Chicken(id, name, color, age, isMolting);
                    console.log(The chicken is: ${chicken2.toString()});
                    chicken2.doStuff();
                    askForAnotherChicken(chicken2);
                });
            });
        });
    });
});

function askForAnotherChicken(chicken2) {
    rl.question("Do you want to see the chicken do its actions again? (yes/no): ", (userOption) => {
        if (userOption.trim().toLowerCase() === "yes") {
            chicken2.doStuff();
            askForAnotherChicken(chicken2);
        } else {
            console.log("Thank you for using the Chicken Farm Simulator");
            rl.close();
        }
    });
}
