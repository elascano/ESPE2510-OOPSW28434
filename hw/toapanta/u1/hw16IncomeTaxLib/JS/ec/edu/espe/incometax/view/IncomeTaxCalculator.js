const { IncomeTax } = require('../calculation/IncomeTax.js');

class IncomeTaxCalculator {
    static async main() {
        console.log("        INCOME TAX CALCULATOR BY KEVIN CHALAN");
        
        const monthlySalary = await IncomeTax.getValidatedDouble("your salary in DOLLARS: ");
        const monthlyExpenses = await IncomeTax.getValidatedDouble("your monthly expenses: ");
        const institutionalSector = await IncomeTax.getValidatedSector();
        
        IncomeTax.showTaxResults(monthlySalary, institutionalSector, monthlyExpenses);
        
        process.exit(0);
    }
}

// Ejecutar la aplicación
IncomeTaxCalculator.main();