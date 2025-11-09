// Author: Bryan Gudino, KNOWLEDGE ENCAPSULATE, @ESPE

import readline from "readline";
import CalculationIncomeTax from "./CalculationIncomeTax.js";

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

function askQuestion(query) {
  return new Promise((resolve) => rl.question(query, (answer) => resolve(answer.trim())));
}

async function main() {
  console.log("ECUADOR TAX CALCULATOR 2025");

  const monthlySalary = parseFloat(await askQuestion("Enter your average monthly salary: "));
  const annualPersonalExpenses = parseFloat(await askQuestion("Enter your total Annual Personal Expenses: "));
  const dependents = parseInt(await askQuestion("Enter Number of Dependents: "));
  const annualWithholding = parseFloat(await askQuestion("Enter Annual Withholdings already paid: "));

  const annualGrossIncome = monthlySalary * 12;
  const annualIEES = annualGrossIncome * 0.0945;

  const balance = CalculationIncomeTax.finalBalance(
    annualGrossIncome,
    annualIEES,
    annualPersonalExpenses,
    dependents,
    annualWithholding
  );

  if (balance > 0) {
    console.log(`RESULT: Tax Payable: $${balance.toFixed(2)}`);
  } else {
    console.log(`Balance in favor: $${Math.abs(balance).toFixed(2)}`);
  }

  rl.close();
}

main();
