import IncomeTax from "./incomeTax.js";

function main() {
  console.log("=== Income Tax Calculator ===\n");

  import('prompt-sync').then(({ default: promptSync }) => {
    const prompt = promptSync({ sigint: true });

    const salary = parseFloat(prompt("Enter your monthly salary (USD): "));
    const expenses = parseFloat(prompt("Enter your monthly deductible expenses (USD): "));

    const taxCalculator = new IncomeTax();

    const monthlyTax = taxCalculator.calculateMonthlyTax(salary, expenses);
    const annualTax = monthlyTax * 12;
    const annualIncome = salary * 12;
    const bracketInfo = taxCalculator.findTaxBracket(annualIncome - expenses * 12);

    console.log("\n-------------------------------------");
    console.log(bracketInfo);
    console.log(`Estimated monthly tax: $${monthlyTax}`);
    console.log(`Estimated annual tax:  $${annualTax}`);
    console.log("-------------------------------------");
  });
}

main();
