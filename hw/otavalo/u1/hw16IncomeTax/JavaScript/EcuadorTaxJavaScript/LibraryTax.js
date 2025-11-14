class IncomeTaxCalculator {
  constructor(monthlyIncome, annualDeductions) {
    this.monthlyIncome = monthlyIncome;
    this.annualDeductions = annualDeductions;
  }

  calculateAnnualTax() {
    const annualIncome = this.monthlyIncome * 12;
    const taxableBase = annualIncome - this.annualDeductions;
    let tax = 0;

    if (taxableBase <= 11902) {
    tax = 0;
    } else if (taxableBase <= 15159) {
        tax = (taxableBase - 11902) * 0.05;
    } else if (taxableBase <= 19682) {
        tax = 163 + (taxableBase - 15159) * 0.10;
    } else if (taxableBase <= 26031) {
        tax = 615 + (taxableBase - 19682) * 0.12;
    } else if (taxableBase <= 34255) {
        tax = 1377 + (taxableBase - 26031) * 0.15;
    } else if (taxableBase <= 45407) {
        tax = 2611 + (taxableBase - 34255) * 0.20;
    } else if (taxableBase <= 60450) {
        tax = 4841 + (taxableBase - 45407) * 0.25;
    } else if (taxableBase <= 80605) {
        tax = 8602 + (taxableBase - 60450) * 0.30;
    } else if (taxableBase <= 107199) {
        tax = 14648 + (taxableBase - 80605) * 0.35;
    } else {
        tax = 23956 + (taxableBase - 107199) * 0.37;
    }

    return Math.max(tax, 0);
  }

  showSummary() {
    const annualTax = this.calculateAnnualTax();
    console.log("\n------------------------------------------");
    console.log(`Monthly income: $${this.monthlyIncome.toFixed(2)}`);
    console.log(`Annual deductions: $${this.annualDeductions.toFixed(2)}`);
    console.log(`Estimated annual income tax: $${annualTax.toFixed(2)}`);
    console.log("------------------------------------------");
  }
}

module.exports = IncomeTaxCalculator;
