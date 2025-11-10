const taxBrackets = [
    [0, 11902, 0, 0],
    [11902, 15159, 0, 5],
    [15159, 19682, 163, 10],
    [19682, 26031, 615, 12],
    [26031, 34255, 1377, 15],
    [34255, 45407, 2611, 20],
    [45407, 60450, 4841, 25],
    [60450, 80605, 8602, 30],
    [80605, 107199, 14648, 35],
    [107199, Infinity, 23956, 37]
];

// Calculate annual tax
function calculateAnnualTax(annualSalary) {
    if (annualSalary <= 0) return 0;
    
    for (let i = taxBrackets.length - 1; i >= 0; i--) {
        const [basic, , tax, percent] = taxBrackets[i];
        if (annualSalary > basic) {
            const excess = annualSalary - basic;
            return tax + (excess * percent / 100);
        }
    }
    return 0;
}

// Calculate monthly tax
function calculateMonthlyTax(monthlySalary, annualExpenses) {
    const annualSalary = monthlySalary * 12;
    const taxBase = Math.max(0, annualSalary - annualExpenses);
    const annualTax = calculateAnnualTax(taxBase);
    return annualTax / 12;
}

// Get tax bracket info
function getTaxBracket(annualSalary) {
    for (const [, max, , percent] of taxBrackets) {
        if (annualSalary <= max) {
            return { max, percent };
        }
    }
    return taxBrackets[taxBrackets.length - 1];
}

module.exports = {
    calculateAnnualTax,
    calculateMonthlyTax,
    getTaxBracket
};