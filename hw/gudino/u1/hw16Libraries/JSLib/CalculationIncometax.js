// Author: Bryan Gudino, KNOWLEDGE ENCAPSULATE, @ESPE

class CalculationIncomeTax {
  static CBF_JANUARY_2025 = 798.31;

  static TABLE_TRAMOS_2025 = [
    { lower: 0, upper: 12081, basic: 0, percent: 0.00 },
    { lower: 12081, upper: 15311, basic: 0, percent: 0.05 },
    { lower: 15311, upper: 19956, basic: 161, percent: 0.10 },
    { lower: 19956, upper: 26458, basic: 626, percent: 0.12 },
    { lower: 26458, upper: 35339, basic: 1406, percent: 0.15 },
    { lower: 35339, upper: 47196, basic: 2748, percent: 0.20 },
    { lower: 47196, upper: 63558, basic: 5120, percent: 0.25 },
    { lower: 63558, upper: 84720, basic: 9211, percent: 0.30 },
    { lower: 84720, upper: 112963, basic: 15560, percent: 0.35 },
    { lower: 112963, upper: Infinity, basic: 25445, percent: 0.37 }
  ];

  static finalBalance(grossIncome, ieesContribution, personalExpenses, familyDependents, withholding) {
    const taxableBase = Math.max(0, grossIncome - ieesContribution);
    const taxDue = this.taxDue(taxableBase);
    const discountExpenses = this.discount(personalExpenses, familyDependents);
    const finalBalance = taxDue - discountExpenses - withholding;
    return finalBalance;
  }

  static taxDue(taxableBase) {
    for (const tramo of this.TABLE_TRAMOS_2025) {
      if (taxableBase <= tramo.upper) {
        const surplus = taxableBase - tramo.lower;
        return tramo.basic + surplus * tramo.percent;
      }
    }
    return 0;
  }

  static discount(personalExpenses, familyDependents) {
    const numBand = this.getTaxBand(familyDependents);
    const maxSpending = numBand * this.CBF_JANUARY_2025;
    const baseDiscount = Math.min(personalExpenses, maxSpending);
    return baseDiscount * 0.18;
  }

  static getTaxBand(dependents) {
    if (dependents <= 0) return 7;
    if (dependents === 1) return 9;
    if (dependents === 2) return 11;
    if (dependents === 3) return 14;
    if (dependents === 4) return 17;
    return 20;
  }
}

export default CalculationIncomeTax;
