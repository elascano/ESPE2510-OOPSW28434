
const readline = require('readline');
const IncomeTax = require('./incomeTax');

class IncomeTaxCalculator {
    
    static question(rl, prompt) {
        return new Promise((resolve) => {
            rl.question(prompt, resolve);
        });
    }
    
    static async main() {
        const rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
        
        console.log("==========================================");
        console.log("        INCOME TAX CALCULATOR");
        console.log("==========================================");
        
        try {
            const salaryInput = await this.question(rl, "Enter your monthly salary: $");
            const expensesInput = await this.question(rl, "Enter your monthly expenses: $");
            const sectorInput = await this.question(rl, "Employment sector (1=Public, 2=Private): ");
            
            const monthlySalary = parseFloat(salaryInput);
            const monthlyExpenses = parseFloat(expensesInput);
            const institutionalSector = parseInt(sectorInput);
            
            IncomeTax.showTaxResults(monthlySalary, institutionalSector, monthlyExpenses);
            
        } catch (error) {
            console.log(`ERROR: ${error.message}`);
        } finally {
            rl.close();
        }
    }
}

if (require.main === module) {
    IncomeTaxCalculator.main();
}

module.exports = IncomeTaxCalculator;