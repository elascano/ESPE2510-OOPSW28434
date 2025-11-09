function _calculateTaxableBase(monthlySalary) {
    const annualIncome = monthlySalary * 12;
    const annualIESSContribution = annualIncome * 0.0945;
    return annualIncome - annualIESSContribution;
}

function _getMaximumDiscount(numberOfFamilyDeficits) {
    switch (numberOfFamilyDeficits) {
        case 0: return 1005.87;
        case 1: return 1293.26;
        case 2: return 1580.65;
        case 3: return 2011.74;
        case 4: return 2442.83;
        case 5:
        default:
            return 2873.92;
    }
}

function _calculateFinalDiscount(annualPersonalExpenses, numberOfFamilyDeficits) {
    const calculatedDiscount = annualPersonalExpenses * 0.18;
    const maximumDiscount = _getMaximumDiscount(numberOfFamilyDeficits);
    return Math.min(calculatedDiscount, maximumDiscount);
}

function _calculateTaxDue(taxBase) {
    let basicFraction = 0;
    let basicTax = 0;
    let percentageSurplus = 0;

    if (taxBase <= 12081) {
        basicFraction = 0;
        basicTax = 0;
        percentageSurplus = 0;
    } else if (taxBase <= 15387) {
        basicFraction = 12081;
        basicTax = 0;
        percentageSurplus = 0.05;
    } else if (taxBase <= 19978) {
        basicFraction = 15387;
        basicTax = 165;
        percentageSurplus = 0.10;
    } else if (taxBase <= 26422) {
        basicFraction = 19978;
        basicTax = 624;
        percentageSurplus = 0.12;
    } else if (taxBase <= 34770) {
        basicFraction = 26422;
        basicTax = 1398;
        percentageSurplus = 0.15;
    } else if (taxBase <= 46089) {
        basicFraction = 34770;
        basicTax = 2650;
        percentageSurplus = 0.20;
    } else if (taxBase <= 61359) {
        basicFraction = 46089;
        basicTax = 4914;
        percentageSurplus = 0.25;
    } else if (taxBase <= 81817) {
        basicFraction = 61359;
        basicTax = 8731;
        percentageSurplus = 0.30;
    } else if (taxBase <= 108810) {
        basicFraction = 81817;
        basicTax = 14869;
        percentageSurplus = 0.35;
    } else {
        basicFraction = 108810;
        basicTax = 24316;
        percentageSurplus = 0.37;
    }

    const surplus = taxBase - basicFraction;
    const taxDue = basicTax + (surplus * percentageSurplus);
    return taxDue;
}

export function incomeTaxCalculation(monthlySalary, annualPersonalExpenses, numberOfFamilyDeficits) {
    
    const taxBase = _calculateTaxableBase(monthlySalary);
    const taxDue = _calculateTaxDue(taxBase);

    if (taxDue === 0.0) {
        return 0.0;
    }

    const finalDiscount = _calculateFinalDiscount(annualPersonalExpenses, numberOfFamilyDeficits);
    const taxToPay = taxDue - finalDiscount;

    return Math.max(0.0, taxToPay);
}