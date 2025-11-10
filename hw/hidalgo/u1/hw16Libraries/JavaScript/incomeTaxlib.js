const BASE_SALARY = 798.31;

const TAX_TABLE = [
  [0, 12081, 0, 0],
  [12081, 15311, 0, 0.05],
  [15311, 19956, 161, 0.10],
  [19956, 26458, 626, 0.12],
  [26458, 35339, 1406, 0.15],
  [35339, 47196, 2748, 0.20],
  [47196, 63558, 5120, 0.25],
  [63558, 84720, 9211, 0.30],
  [84720, 112963, 15560, 0.35],
  [112963, Infinity, 25445, 0.37]
];

function getLimitMultiplier(familyMembers) {
  return familyMembers <= 0 ? 7
       : familyMembers === 1 ? 9
       : familyMembers === 2 ? 11
       : familyMembers === 3 ? 14
       : familyMembers === 4 ? 17
       : 20;
}

function calculateTax(base) {
  for (let i = 0; i < TAX_TABLE.length; i++) {
    const [min, max, fixed, percent] = TAX_TABLE[i];
    if (base <= max) {
      return fixed + (base - min) * percent;
    }
  }
  return 0;
}

function calculateDiscount(expenses, dependents) {
  const cap = getLimitMultiplier(dependents) * BASE_SALARY;
  const approved = expenses > cap ? cap : expenses;
  return approved * 0.18;
}

export const finalBalance = (yearIncome, iess, spending, dependents, retained) => {
  const taxable = yearIncome - iess < 0 ? 0 : yearIncome - iess;
  const tax = calculateTax(taxable);
  const discount = calculateDiscount(spending, dependents);
  return tax - discount - retained;
};
