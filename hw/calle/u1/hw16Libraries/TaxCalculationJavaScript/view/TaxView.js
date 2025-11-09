const readline = require('readline/promises');
const { stdin: input, stdout: output } = require('process');

const rl = readline.createInterface({ input, output });

class TaxView {
    
    async getTaxData() {
        console.log("\n=============================================");
        console.log("  INCOME TAX CALCULATION TOOL 2024 - ECUADOR");
        console.log("=============================================");
        console.log("\n ANNUAL INCOME DATA");
        console.log("---------------------------------------------");

        try {
            const monthlySalary = await rl.question("  Enter Gross Monthly Salary (USD): ");
            const personalExpenses = await rl.question("  Enter Total Projected Personal Expenses: ");
            const dependents = await rl.question("  Enter Number of Dependents: ");
            const annualWithholdings = await rl.question("  Enter Annual Income Tax Withheld (Paid): ");
            
            rl.close(); 
            
            return {
                monthlySalary: parseFloat(monthlySalary),
                personalExpenses: parseFloat(personalExpenses),
                dependents: parseInt(dependents),
                annualWithholdings: parseFloat(annualWithholdings)
            };
        } catch (error) {
            rl.close();
            return { monthlySalary: NaN }; 
        }
    }

    displayTaxReport(results) {
        const { 
            annualTaxableBase, 
            annualTaxDue, 
            dependents, 
            taxRebateAmount, 
            finalAnnualTax, 
            annualWithholdings, 
            finalBalance 
        } = results;

        const formatUSD = (amount) => `$${amount.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}`;


        if (finalBalance > 0) {
            console.log(`**TAX PENDING TO PAY:** ${formatUSD(finalBalance)}`);
        } else if (finalBalance < 0) {
            console.log(`**BALANCE IN FAVOR (REFUND):** ${formatUSD(Math.abs(finalBalance))}`);
        } else {
            console.log("**NO BALANCE DUE**");
        }
        
        console.log("=============================================");
    }

    displayError(message) {
        console.error(`\n[ERROR] ${message}\n`);
    }
}

module.exports = { TaxView };