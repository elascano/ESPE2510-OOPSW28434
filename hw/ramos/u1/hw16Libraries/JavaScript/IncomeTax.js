class IncomeTax {
  constructor() {
    this.brackets = [
      [0, 11902, 0, 0.00],
      [11902, 15159, 0, 0.05],
      [15159, 19682, 163, 0.10],
      [19682, 26031, 617, 0.12],
      [26031, 34255, 1377, 0.15],
      [34255, 45407, 2611, 0.20],
      [45407, 60450, 4841, 0.25],
      [60450, 80665, 8591, 0.30],
      [80665, 107199, 14466, 0.35],
      [107199, Infinity, 23956, 0.37]
    ];
  }

  calculateAnnualTax(annualIncome) {
    for (let [lower, upper, baseTax, rate] of this.brackets) {
      if (annualIncome <= upper) {
        const excess = annualIncome - lower;
        const totalTax = baseTax + excess * rate;
        return Number(totalTax.toFixed(2));
      }
    }
    return 0.0;
  }

  calculateMonthlyTax(monthlySalary, monthlyExpenses) {
    const annualIncome = monthlySalary * 12;
    const annualExpenses = monthlyExpenses * 12;
    const taxableBase = Math.max(0, annualIncome - annualExpenses);

    const annualTax = this.calculateAnnualTax(taxableBase);
    const monthlyTax = annualTax / 12;

    return Number(monthlyTax.toFixed(2));
  }

  findTaxBracket(annualIncome) {
    for (let [lower, upper, , rate] of this.brackets) {
      if (annualIncome <= upper) {
        return `Your income is in the range $${lower} - $${upper} with a ${Math.round(rate * 100)}% tax rate.`;
      }
    }
    return "Income exceeds all defined brackets.";
  }
}

export default IncomeTax;
