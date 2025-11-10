const readline = require('readline');
const { calculateMonthlyTax, getTaxBracket } = require('./tax');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

async function ask(question) {
    return new Promise(resolve => {
        rl.question(question, resolve);
    });
}

async function main() {
    console.log("=== INCOME TAX CALCULATOR ===\n");
    
    const salary = parseFloat(await ask("Monthly salary: $"));
    const expenses = parseFloat(await ask("Annual expenses: $"));
    
    const monthlyTax = calculateMonthlyTax(salary, expenses);
    const annualSalary = salary * 12;
    const taxBase = Math.max(0, annualSalary - expenses);
    const bracket = getTaxBracket(taxBase);
    
    console.log("\n=== RESULTS ===");
    console.log(`Monthly tax: $${monthlyTax.toFixed(2)}`);
    console.log(`Annual tax: $${(monthlyTax * 12).toFixed(2)}`);
    console.log(`Net monthly: $${(salary - monthlyTax).toFixed(2)}`);
    console.log(`Tax bracket: Up to $${bracket.max} (${bracket.percent}% marginal)`);
    
    rl.close();
}

main().catch(console.error);