const CBD_JANUARY_2025 = 798.31;

const TABLE_TRAMOS_2025 = [
    { lowerLimit: 0, upperLimit: 12081, basicTax: 0, excessPercentage: 0.00 },
    { lowerLimit: 12081, upperLimit: 15311, basicTax: 0, excessPercentage: 0.05 },
    { lowerLimit: 15311, upperLimit: 19956, basicTax: 161, excessPercentage: 0.10 },
    { lowerLimit: 19956, upperLimit: 26458, basicTax: 626, excessPercentage: 0.12 },
    { lowerLimit: 26458, upperLimit: 35339, basicTax: 1406, excessPercentage: 0.15 },
    { lowerLimit: 35339, upperLimit: 47196, basicTax: 2748, excessPercentage: 0.20 },
    { lowerLimit: 47196, upperLimit: 63558, basicTax: 5120, excessPercentage: 0.25 },
    { lowerLimit: 63558, upperLimit: 84720, basicTax: 9211, excessPercentage: 0.30 },
    { lowerLimit: 84720, upperLimit: 112963, basicTax: 15560, excessPercentage: 0.35 },
    { lowerLimit: 112963, upperLimit: Infinity, basicTax: 25445, excessPercentage: 0.37 }
];

const getTaxBand = (depents) => {
    if (depents <= 0) {
        return 7;
    } else if (depents === 1) {
        return 9;
    } else if (depents === 2) {
        return 11;
    } else if (depents === 3) {
        return 14;
    } else if (depents === 4) {
        return 17;
    } else {
        return 20;
    }
};

const taxDue = (taxableBase) => {
    for (const tramo of TABLE_TRAMOS_2025) {
        if (taxableBase <= tramo.upperLimit) {
            const surplus = taxableBase - tramo.lowerLimit;
            const surplusTax = surplus * tramo.excessPercentage;
            return tramo.basicTax + surplusTax;
        }
    }
    return 0; 
};

const discount = (personalExpenses, familyDepents) => {
    const numTaxBand = getTaxBand(familyDepents);
    const maximunSpendingLimit = numTaxBand * CBD_JANUARY_2025;
    const baseDiscount = Math.min(personalExpenses, maximunSpendingLimit);
    return baseDiscount * 0.18;
};

export const finalBalance = (grossIncome, ieesContribution, personalExpenses, familyDepents, withholding) => {
    const taxableBase = Math.max(0, grossIncome - ieesContribution);
    const taxDueValue = taxDue(taxableBase);
    const discountExpenses = discount(personalExpenses, familyDepents);
    const finalBalanceValue = taxDueValue - discountExpenses - withholding;
    
    return finalBalanceValue;
};

