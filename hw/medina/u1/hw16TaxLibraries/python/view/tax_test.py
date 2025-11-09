from taxOperations.ecuador_tax_calculator import EcuadorTaxCalculator

income = 121343.76
deductions = 35000.74

tax_base = EcuadorTaxCalculator.calculate_taxable_base(income, deductions)
bracket = EcuadorTaxCalculator.find_tax_bracket(tax_base)
total_tax = EcuadorTaxCalculator.calculate_total_tax(tax_base)

print("=== ECUADOR INCOME TAX REPORT ===")
print(f"Gross Income: ${income:,.2f}")
print(f"Allowable Deductions: ${deductions:,.2f}")
print(f"Taxable Base: ${tax_base:,.2f}")
print("-----------------------------------")
print(bracket)
print(f"Total Tax to Pay: ${total_tax:,.2f}")
print("===================================")
