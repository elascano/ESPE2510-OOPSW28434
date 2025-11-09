const Operation = require('../Operation');
const readline = require('readline'); 
const formatCurrency = (amount) => {
    return '$' + amount.toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
};

async function main() {
    const rl = readline.createInterface({ input: process.stdin, output: process.stdout });
    const question = (q) => new Promise(resolve => rl.question(q, ans => resolve(ans)));
    console.log("--- WELCOME TO THE INCOME TAX CALCULATOR BY MARYURI ---");
    
    try {
        const monthlySalary = parseFloat(await question("Enter your average monthly salary: "));
        const annualSpentDeductible = parseFloat(await question("Enter your total Annual Personal Expenses: "));
        const dependents = parseInt(await question("Enter Number of Dependents: "));
        const annualWithholdings = parseFloat(await question("Enter Annual Withholdings (already paid): "));

        if (isNaN(monthlySalary) || isNaN(annualSpentDeductible) || isNaN(dependents) || isNaN(annualWithholdings)) {
             throw new Error("Invalid input.");
        }

        const annualSalary = monthlySalary * 12.0; 
        const taxableBase = Operation.calculateAnnualTaxableBase(annualSalary, annualSpentDeductible, dependents);
        const annualTaxRequired = Operation.calculateAnnualIncomeTax(taxableBase);
        const balance = annualWithholdings - annualTaxRequired;

        console.log("\n----------------------------------------------");
        console.log("Annual Tax REQUIRED: " + formatCurrency(annualTaxRequired));
        console.log("Annual Withholdings PAID: " + formatCurrency(annualWithholdings));
        console.log("----------------------------------------------");

        if (balance >= 0.01) {
            console.log("RESULT: Balance in favor (Refund): " + formatCurrency(balance));
        } else if (balance <= -0.01) {
            console.log("RESULT: Tax to pay this year is: " + formatCurrency(Math.abs(balance)));
        } else {
            console.log("RESULT: Final balance is zero.");
        }
        console.log("==============================================");

    } catch (e) {
        console.error(`\nError: Please ensure you entered valid numerical values.`);
    } finally {
        rl.close();
    }
}
if (require.main === module) main();
