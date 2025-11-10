import { finalBalance } from './incomeTaxlib.js';
import readline from 'node:readline';

class ConsoleReader {
    constructor() {
        this.io = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
    }

    ask(text) {
        return new Promise(res => this.io.question(text, res));
    }

    async getNumberFloat(label) {
        let number;
        do {
            const entry = await this.ask(label);
            number = Number(entry);
            if (isNaN(number)) {
                console.log(" Invalid number. Example: 2500.75");
            }
        } while (isNaN(number));
        return number;
    }

    async getNumberInt(label) {
        let number;
        do {
            const entry = await this.ask(label);
            number = Number(entry);
            if (!Number.isInteger(number)) {
                console.log(" Invalid integer. Example: 3");
            }
        } while (!Number.isInteger(number));
        return number;
    }

    finish() {
        this.io.close();
    }
}

class TaxSystem {
    constructor(monthSalary, personalSpend, dependents, retention) {
        this.monthSalary = monthSalary;
        this.personalSpend = personalSpend;
        this.dependents = dependents;
        this.retentionPaid = retention;
    }

    calculateOutcome() {
        const yearIncome = this.monthSalary * 12;
        const iess = yearIncome * 0.0945;
        return finalBalance(yearIncome, iess, this.personalSpend, this.dependents, this.retentionPaid);
    }

    showResult(result) {
        console.log("\n======== TAX RESULTS 2025 ========");
        result > 0
            ? console.log(`Amount to pay: $${result.toFixed(2)}`)
            : console.log(`Balance in your favor: $${Math.abs(result).toFixed(2)}`);
        console.log("==================================");
    }
}

async function startApp() {
    console.log("\n*** ECUADOR INCOME TAX CALCULATOR 2025 ***\n");

    const reader = new ConsoleReader();

    const salary = await reader.getNumberFloat("Monthly average salary: ");
    const expenses = await reader.getNumberFloat("Total personal expenses per year: ");
    const familyMembers = await reader.getNumberInt("How many dependents do you have?: ");
    const alreadyPaid = await reader.getNumberFloat("Withholdings paid in the year: ");

    reader.finish();

    const taxProcess = new TaxSystem(salary, expenses, familyMembers, alreadyPaid);
    const result = taxProcess.calculateOutcome();

    taxProcess.showResult(result);
}

startApp();
