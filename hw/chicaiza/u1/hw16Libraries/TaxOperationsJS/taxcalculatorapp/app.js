// Importamos la libreria local
import { TaxCalculator, version } from "../taxoperations/index.js";

console.log(`Using taxoperations version: ${version}`);

const salary = 1800;
const expenses = 250;

const taxCalc = new TaxCalculator(salary, expenses);

console.log(`Taxable income: $${taxCalc.taxableIncome().toFixed(2)}`);
console.log(`Monthly tax: $${taxCalc.taxMonthly().toFixed(2)}`);
console.log(`Annual tax: $${taxCalc.taxAnnual().toFixed(2)}`);
console.log(`Net monthly salary: $${taxCalc.netSalaryMonthly().toFixed(2)}`);
console.log(`Net annual salary: $${taxCalc.netSalaryAnnual().toFixed(2)}`);
