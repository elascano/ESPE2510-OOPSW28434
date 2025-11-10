import readline from "readline";
import { calculateTax } from "./IncomeTaxJavaScript.js";

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function showMenu() {
    console.log("====== INCOME TAX CALCULATOR ======");
    console.log("1. Calculate Income Tax");
    console.log("2. Exit");
    rl.question("Choose an option: ", handleMenu);
}

function handleMenu(option) {
    switch (option) {
        case "1":
            rl.question("Enter your monthly salary: $", (salary) => {
                rl.question("Enter your monthly expenses: $", (expenses) => {
                    const tax = calculateTax(parseFloat(salary), parseFloat(expenses));
                    console.log("------------------------------------");
                    console.log("Your annual income tax is: $" + tax.toFixed(2));
                    console.log("------------------------------------\n");
                    showMenu();
                });
            });
            break;

        case "2":
            console.log("Goodbye!");
            rl.close();
            break;

        default:
            console.log("Invalid option. Try again.\n");
            showMenu();
    }
}

showMenu();