class TaxModel {
    
    static TAX_TABLE_2024 = [
        [0, 11902, 0, 0.0],
        [11902, 15159, 0, 0.05],
        [15159, 19682, 163, 0.10],
        [19682, 26031, 615, 0.12],
        [26031, 34255, 1377, 0.15],
        [34255, 45407, 2611, 0.20],
        [45407, 60450, 4841, 0.25],
        [60450, 80605, 8602, 0.30],
        [80605, 107199, 14648, 0.35],
        [107199, Infinity, 23956, 0.37]
    ];

    static IESS_PERCENTAGE = 0.0945;
    static FAMILY_BASKET_COST = 789.57;
    static MAX_DEPENDENTS = 5;
    static MIN_INCOME_FOR_REBATE = 24652.5;

    calculateAnnualTaxableBase(monthlySalary) {
        const annualIncome = monthlySalary * 12;
        const annualIESS = annualIncome * TaxModel.IESS_PERCENTAGE;
        return annualIncome - annualIESS;
    }

    calculateAnnualIncomeTax(taxableBase) {
        if (taxableBase <= TaxModel.TAX_TABLE_2024[0][1]) {
            return 0.0;
        }

        let annualTax = 0.0;
        
        for (const row of TaxModel.TAX_TABLE_2024) {
            const [basicFraction, excessUpTo, basicTax, excessTaxPercentage] = row;
            if (taxableBase > basicFraction && taxableBase <= excessUpTo) {
                const excessFraction = taxableBase - basicFraction;
                const excessTax = excessFraction * excessTaxPercentage;
                annualTax = basicTax + excessTax;
                break;
            }
        }
        return annualTax;
    }

    calculateTaxRebate(personalExpenses, dependents, annualGrossIncome) {
        const dependentFactor = 1.0 + Math.min(dependents, TaxModel.MAX_DEPENDENTS);
        const spendingLimit = TaxModel.FAMILY_BASKET_COST * 7.0 * dependentFactor / 5.0;
        const maxSpendingLimit = 20000.0;
        const finalSpendingLimit = Math.min(spendingLimit, maxSpendingLimit);

        const expensesToApply = Math.min(personalExpenses, finalSpendingLimit);
        let rebatePercentage = 0.10;
        
        if (annualGrossIncome <= TaxModel.MIN_INCOME_FOR_REBATE) {
            rebatePercentage = 0.20;
        }
        
        const maxRebateAmount = (TaxModel.FAMILY_BASKET_COST * 7.0) * rebatePercentage;
        const calculatedRebate = expensesToApply * rebatePercentage;

        return Math.min(calculatedRebate, maxRebateAmount);
    }
}

module.exports = { TaxModel };