from IncomeTax import IncomeTax

def main():
    print("=== Ecuador Income Tax Calculator (Year 2024) ===\n")

    salary = float(input("Enter your monthly salary (USD): "))
    expenses = float(input("Enter your monthly deductible expenses (USD): "))

    tax_calculator = IncomeTax()

    monthly_tax = tax_calculator.calculate_monthly_tax(salary, expenses)
    annual_tax = monthly_tax * 12

    # Determine tax bracket
    annual_income = salary * 12
    bracket_info = tax_calculator.find_tax_bracket(annual_income - expenses * 12)

    print("\n-------------------------------------")
    print(bracket_info)
    print(f"Estimated monthly tax: ${monthly_tax}")
    print(f"Estimated annual tax:  ${annual_tax}")
    print("-------------------------------------")

if __name__ == "__main__":
    main()

