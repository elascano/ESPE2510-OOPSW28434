
class Tax {
  constructor(monthlySalary, deductibleExpenses) {
    this.monthlySalary = monthlySalary;
    this.deductibleExpenses = deductibleExpenses;
  }

  calculateTax() {
    const annualIncome = this.monthlySalary * 12;
    const taxableBase = Math.max(0, annualIncome - this.deductibleExpenses);

    const taxTable = [
      [0, 12081, 0, 0],
      [12081, 15387, 0, 0.05],
      [15387, 19978, 165, 0.10],
      [19978, 26422, 624, 0.12],
      [26422, 34770, 1398, 0.15],
      [34770, 46089, 2650, 0.20],
      [46089, 61359, 4914, 0.25],
      [61359, 81817, 8731, 0.30],
      [81817, 108810, 14869, 0.35],
      [108810, Infinity, 24316, 0.37],
    ];

    let baseTax = 0, rate = 0, fractionMin = 0;
    for (const row of taxTable) {
      if (taxableBase >= row[0] && taxableBase < row[1]) {
        [fractionMin, , baseTax, rate] = row;
        break;
      }
    }

    let annualTax = baseTax + (taxableBase - fractionMin) * rate;
    if (annualTax < 0) annualTax = 0;
    const monthlyTax = annualTax / 12;

    return { annualIncome, deductibleExpenses: this.deductibleExpenses, taxableBase, annualTax, monthlyTax };
  }
}

export default Tax;
