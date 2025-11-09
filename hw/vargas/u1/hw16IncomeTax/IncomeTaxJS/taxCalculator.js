import { incomeTaxCalculation } from './incomeTaxCalculation.js'; 
import * as readline from 'node:readline/promises';
import { stdin as input, stdout as output } from 'node:process';

async function main() {
    
    const rl = readline.createInterface({ input, output });

    console.log("-----Welcome to Income Tax Calculation-----");

    const monthlySalary = parseFloat(
        await rl.question("Enter your monthly salary: ")
    );

    const annualPersonalExpenses = parseFloat(
        await rl.question("Enter your annual personal expenses: ")
    );

    const numberOfFamilyDeficits = parseInt(
        await rl.question("Enter your number of family deficits: "), 10
    );

    rl.close();

    const incomeTax = incomeTaxCalculation(
        monthlySalary, 
        annualPersonalExpenses, 
        numberOfFamilyDeficits
    );

    console.log(`Your Tax to pay this year is: ${incomeTax.toFixed(2)}`);
}

main();