export const version = "0.2.0";

export class TaxCalculator {
  constructor(salaryMonthly, expenses = 0) {
    if (salaryMonthly < 0 || expenses < 0) {
      throw new Error("Salary and expenses must be non-negative numbers.");
    }
    this.salaryMonthly = salaryMonthly;
    this.expenses = expenses;
    this.taxBrackets = [
      { lower: 0, upper: 500, rate: 0.0 },
      { lower: 500.01, upper: 1000, rate: 0.1 },
      { lower: 1000.01, upper: 2000, rate: 0.15 },
      { lower: 2000.01, upper: Infinity, rate: 0.2 },
    ];
  }

  taxableIncome() { return Math.max(this.salaryMonthly - this.expenses, 0); }
  taxMonthly() { 
    const income = this.taxableIncome();
    for (const b of this.taxBrackets) { if (income >= b.lower && income <= b.upper) return income * b.rate; }
    return 0;
  }
  taxAnnual() { return this.taxMonthly() * 12; }
  netSalaryMonthly() { return this.salaryMonthly - this.taxMonthly(); }
  netSalaryAnnual() { return this.netSalaryMonthly() * 12; }
}
