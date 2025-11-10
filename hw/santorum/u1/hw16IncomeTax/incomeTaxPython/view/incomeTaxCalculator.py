import sys
import os
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), "..")))

from incomeTax.tax import Tax  # importa la librería como en Java

print("- - - - - - - - - - - - -")
print("  INCOME TAX CALCULATOR  ")
print("- - - - - - - - - - - - -")

monthly_salary = float(input("Enter your monthly salary: "))
deductible_expenses = float(input("Enter your annual deductible expenses: "))

calculator = Tax(monthly_salary, deductible_expenses)
result = calculator.calculate_tax()

print("\n- - - - - - - - - RESULTS - - - - - - - - -")

def print_line(label, value):
    print(f"{label.ljust(30)} ${value:,.2f}")

print_line("Annual income:", result["annual_income"])
print_line("Deductible expenses:", result["deductible_expenses"])
print_line("Taxable base:", result["taxable_base"])
print_line("Annual tax:", result["annual_tax"])
print_line("Monthly tax:", result["monthly_tax"])

print("- - - - - - - - - - - - - - - - - - - - - -")
