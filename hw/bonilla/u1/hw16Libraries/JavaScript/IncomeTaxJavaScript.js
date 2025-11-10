export function calculateTax(monthlySalary, monthlyExpenses) {
    const annualIncome = monthlySalary * 12;
    const deductible = monthlyExpenses * 12;
    const taxableIncome = annualIncome - deductible;

    if (taxableIncome <= 0) return 0;

    let tax = 0;

    if (taxableIncome <= 12000) {
        tax = taxableIncome * 0.05;
    } else if (taxableIncome <= 25000) {
        tax = 12000 * 0.05 + (taxableIncome - 12000) * 0.10;
    } else if (taxableIncome <= 40000) {
        tax = 12000 * 0.05 + 13000 * 0.10 + (taxableIncome - 25000) * 0.15;
    } else {
        tax = 12000 * 0.05 + 13000 * 0.10 + 15000 * 0.15 + (taxableIncome - 40000) * 0.20;
    }

    return tax;
}