class Operation {
    static FBE = 11902.00;
    static BASE_UVC = 5355.90;
    static ANNUAL_TAX_BRACKETS = [
        [0.00, 0.00, 0.00],       
        [11902.00, 0.00, 0.05],   
        [15159.00, 163.00, 0.10], 
        [19682.00, 615.00, 0.12], 
        [26031.00, 1377.00, 0.15],
        [34255.00, 2611.00, 0.20],
        [45407.00, 4841.00, 0.25],
        [60450.00, 8602.00, 0.30],
        [80605.00, 14648.00, 0.35],
        [107199.00, 23956.00, 0.37]
    ];

    static calculateAnnualTaxableBase(annualSalary, annualSpentDeductible, dependents) {
        const maxDeductibleExpenses = this.BASE_UVC * (1 + 0.5 * dependents); 
        const actualDeductibleExpenses = Math.min(annualSpentDeductible, maxDeductibleExpenses);
        const incomeAfterExpenses = annualSalary - actualDeductibleExpenses;
        const taxableBase = incomeAfterExpenses - this.FBE;
        
        return Math.max(0, taxableBase);
    }

    static calculateAnnualIncomeTax(taxableBase) {
        let annualTax = 0.0;

        for (const bracket of this.ANNUAL_TAX_BRACKETS) {
            const [basicFraction, fixedTax, marginalRate] = bracket;
            
            if (taxableBase >= basicFraction) {
                const currentIndex = this.ANNUAL_TAX_BRACKETS.indexOf(bracket);
                const upTo = (currentIndex < this.ANNUAL_TAX_BRACKETS.length - 1) 
                            ? this.ANNUAL_TAX_BRACKETS[currentIndex + 1][0] 
                            : Infinity; 

                annualTax = fixedTax;
                let amountTaxedInBracket = taxableBase - basicFraction;
                
                if (taxableBase < upTo) {
                    amountTaxedInBracket = Math.min(amountTaxedInBracket, upTo - basicFraction);
                }
                
                annualTax += amountTaxedInBracket * marginalRate;
                
                if (taxableBase < upTo) {
                    break; 
                }
            }
        }
        
        return annualTax;
    }
}
module.exports = Operation;
