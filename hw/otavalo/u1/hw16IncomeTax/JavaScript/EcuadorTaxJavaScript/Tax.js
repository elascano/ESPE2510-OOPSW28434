const readline = require("readline");
const IncomeTaxCalculator = require('./LibraryTax');

const information = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

information.question("Enter your monthly income: ", (monthlyIncomeInput) => {
  information.question("Enter your annual deductions: ", (annualDeductionsInput) => {

    const monthlyIncome = parseFloat(monthlyIncomeInput.replace(",", "."));
    const annualDeductions = parseFloat(annualDeductionsInput.replace(",", "."));

    const calculator = new IncomeTaxCalculator(monthlyIncome, annualDeductions);
    calculator.showSummary();

    information.close(); 
  });
});
