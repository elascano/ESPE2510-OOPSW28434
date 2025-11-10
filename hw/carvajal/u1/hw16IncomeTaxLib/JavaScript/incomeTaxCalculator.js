import readline from "readline";
import { IncomeTax } from "./incomeTax.js";

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

function askQuestion(query) {
  return new Promise((resolve) => rl.question(query, resolve));
}

async function main() {
  console.log("==================================================");
  console.log("             INCOME TAX CALCULATOR");
  console.log("==================================================");

  try {
    const salaryInput = await askQuestion("Enter your monthly salary: $");
    const expensesInput = await askQuestion("Enter your monthly expenses: $");
    const sectorInput = await askQuestion("Employment sector (1=Public, 2=Private): ");

    const monthlySalary = parseFloat(salaryInput);
    const monthlyExpenses = parseFloat(expensesInput);
    const institutionalSector = parseInt(sectorInput);

    IncomeTax.showTaxResults(monthlySalary, institutionalSector, monthlyExpenses);
  } catch (error) {
    console.error("ERROR:", error.message);
  } finally {
    rl.close();
  }
}

main();