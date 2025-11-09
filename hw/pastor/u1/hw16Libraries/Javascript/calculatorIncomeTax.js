import {finalBalance} from './incomeTaxlib.js';
import * as readline from 'node:readline';

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const askQuestion = (query) => {
    return new Promise((resolve) => {
        rl.question(query, resolve);
    });
};

const readFloat = async (message) => {
    while (true) {
        const input = await askQuestion(message);
        const value = parseFloat(input);
        if (!isNaN(value)) {
            return value;
        }
        console.log("Incorrect data, please enter a number (e.g 1254.66)");
    }
};

const readInt = async (message) => {
    while (true) {
        const input = await askQuestion(message);
        const value = parseInt(input, 10);
        if (!isNaN(value) && parseFloat(input) === value) {
            return value;
        }
        console.log("Incorrect data, please enter a number (e.g 16)");
    }
};

async function main() {
    console.log("----- WELCOME TO THE INCOME TAX ECUADOR 2025 BY MATHEWS PASTOR -----");

    const monthlySalary = await readFloat("Enter your average monthly salary: ");
    const annualPersonalExpenses = await readFloat("Enter your total Annual Personal Expenses: ");
    const depents = await readInt("Enter Number of Dependents: ");
    const annualWithholding = await readFloat("Enter Annual Withholdings (already paid): ");
    rl.close();

    const annualGrossIncome = monthlySalary * 12;
    const annualIeesContributions = annualGrossIncome * 0.0945;

    const balance = finalBalance(
        annualGrossIncome,
        annualIeesContributions,
        annualPersonalExpenses,
        depents,
        annualWithholding
    );

    if (balance > 0) {
        console.log(`\nRESULT: Tax Payable: $${balance.toFixed(2)}`);
    } else {
        console.log(`\nRESULT: Balance in favor: $${Math.abs(balance).toFixed(2)}`);
    }
}

main();