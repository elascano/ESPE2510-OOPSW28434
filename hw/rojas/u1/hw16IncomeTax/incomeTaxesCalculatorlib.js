//Author: Josue Rojas
// libraryJS.js

const BRACKETS = [
  { lower: 0,      upper: 12081,   rate: 0.00, baseTax: 0 },
  { lower: 12081,  upper: 15387,   rate: 0.05, baseTax: 0 },
  { lower: 15387,  upper: 19978,   rate: 0.10, baseTax: 165 },
  { lower: 19978,  upper: 26422,   rate: 0.12, baseTax: 624 },
  { lower: 26422,  upper: 34770,   rate: 0.15, baseTax: 1398 },
  { lower: 34770,  upper: 46089,   rate: 0.20, baseTax: 2650 },
  { lower: 46089,  upper: 61359,   rate: 0.25, baseTax: 4941 },
  { lower: 61359,  upper: 81817,   rate: 0.30, baseTax: 8731 },
  { lower: 81817,  upper: 108810,  rate: 0.35, baseTax: 14869 },
  { lower: 108810, upper: Infinity, rate: 0.37, baseTax: 24316 },
];

const CFB_2025 = 798.31;

function canastas(deps) {
  if (deps <= 0) return 7;
  if (deps === 1) return 9;
  if (deps === 2) return 11;
  if (deps === 3) return 14;
  if (deps === 4) return 17;
  return 20; // 5 o más
}

/**
 * @param {number} monthlySalary 
 * @param {number} annualSpent   
 * @param {number} dependents   
 * @returns {{annualGross:number,taxableIncome:number,causedTax:number,rebate:number,annualTax:number,monthlyWithholding:number}}
 */
function computeIncomeTaxEC(monthlySalary, annualSpent, dependents = 0) {
  if (!(monthlySalary >= 0) || !(annualSpent >= 0) || !(dependents >= 0)) {
    throw new Error("Inputs must be non-negative");
  }
  const annualGross = monthlySalary * 12;
  const taxable = annualGross;

  const b = BRACKETS.find(x => taxable >= x.lower && taxable < x.upper);
  const causedTax = b.baseTax + (taxable - b.lower) * b.rate;

  const cap = CFB_2025 * canastas(dependents);
  const rebate = 0.18 * Math.min(annualSpent, cap);

  const annualTax = Math.max(0, causedTax - rebate);
  const monthlyWithholding = annualTax / 12;

  return { annualGross, taxableIncome: taxable, causedTax, rebate, annualTax, monthlyWithholding };
}

module.exports = { computeIncomeTaxEC };