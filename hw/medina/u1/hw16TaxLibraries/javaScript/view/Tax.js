import EcuadorTaxCalculator from "../ec/edu/espe/taxOperations/EcuadorTaxCalculator.js";

const income = 121343.76;
const deductions = 35000.74;

const taxBase = EcuadorTaxCalculator.calculateTaxableBase(income, deductions);
const bracket = EcuadorTaxCalculator.findTaxBracket(taxBase);
const totalTax = EcuadorTaxCalculator.calculateTotalTax(taxBase);

function formatCurrency(value) {
  return value.toLocaleString("es-EC", {
    style: "currency",
    currency: "USD",
    minimumFractionDigits: 2,
  });
}

console.log("=== ECUADOR INCOME TAX REPORT ===");
console.log("Gross Income:", formatCurrency(income));
console.log("Allowable Deductions:", formatCurrency(deductions));
console.log("Taxable Base:", formatCurrency(taxBase));
console.log("-----------------------------------");
console.log(bracket);
console.log("Total Tax to Pay:", formatCurrency(totalTax));
console.log("===================================");
console.log("BUILD SUCCESSFUL (total time: 0 seconds)");
