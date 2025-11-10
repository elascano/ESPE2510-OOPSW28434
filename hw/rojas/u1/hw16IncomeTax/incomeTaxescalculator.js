//Author: Josue Rojas
// useOfLibrary.js

const { computeIncomeTaxEC } = require("./incomeTaxesCalculatorlib");
const readline = require("readline");

const rl = readline.createInterface({ input: process.stdin, output: process.stdout });
const ask = q => new Promise(res => rl.question(q, res));

async function main() {
  console.log("----- Welcome to Income Tax Calculation -----");

  while (true) {
    try {
      const monthly = parseFloat((await ask("Enter your monthly salary:\n")).trim());
      const spent   = parseFloat((await ask("Enter your annual personal expenses:\n")).trim());
      const deps    = parseInt((await ask("Enter your number of family dependents:\n")).trim(), 10);

      const r = computeIncomeTaxEC(monthly, spent, isNaN(deps) ? 0 : deps);
      console.log(`\nYour Tax to pay this year is: ${r.annualTax.toFixed(1)}\n`);
      // console.log(`Monthly withholding: ${r.monthlyWithholding.toFixed(2)}`);
    } catch (e) {
      console.log("Input error:", e.message);
    }

    const again = (await ask("Would you like to calculate again? (s/n): ")).trim().toLowerCase();
    if (again !== "s") { console.log("Goodbye!"); break; }
  }
  rl.close();
}

main();
