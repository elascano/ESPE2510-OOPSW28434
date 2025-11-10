from taxoperations import TaxCalculator, __version__

print(f"Usando taxoperations versión {__version__}")

salary = 1800
expenses = 250

tax_calc = TaxCalculator(salary, expenses)

print(f"Ingreso sujeto a impuestos: ${tax_calc.taxable_income():.2f}")
print(f"Impuesto mensual: ${tax_calc.tax_monthly():.2f}")
print(f"Impuesto anual: ${tax_calc.tax_annual():.2f}")
print(f"Salario neto mensual: ${tax_calc.net_salary_monthly():.2f}")
print(f"Salario neto anual: ${tax_calc.net_salary_annual():.2f}")