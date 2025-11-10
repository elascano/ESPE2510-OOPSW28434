import Tax from "../incomeTax/tax.js";
import readline from "readline";


const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

console.log("- - - - - - - - - - - - -");
console.log("  INCOME TAX CALCULATOR  ");
console.log("- - - - - - - - - - - - -");

rl.question("Enter your monthly salary: ", (salaryInput) => {
  rl.question("Enter your annual deductible expenses: ", (deductibleInput) => {
    const monthlySalary = parseFloat(salaryInput);
    const deductibleExpenses = parseFloat(deductibleInput);

    const calculator = new Tax(monthlySalary, deductibleExpenses);
    const result = calculator.calculateTax();

    console.log("\n- - - - - - - - - RESULTS - - - - - - - - -");

    // Igual que el Java, alineamos con padEnd y 2 decimales
    const printLine = (label, value) =>
      console.log(`${label.padEnd(30)} $${value.toFixed(2)}`);

    printLine("Annual income:", result.annualIncome);
    printLine("Deductible expenses:", result.deductibleExpenses);
    printLine("Taxable base:", result.taxableBase);
    printLine("Annual tax:", result.annualTax);
    printLine("Monthly tax:", result.monthlyTax);

    console.log("- - - - - - - - - - - - - - - - - - - - - -");

    rl.close();
  });
});
