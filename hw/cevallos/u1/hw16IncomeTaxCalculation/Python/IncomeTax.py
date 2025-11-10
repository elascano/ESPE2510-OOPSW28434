from TaxCompute import *

def main():
    print("=== INCOME TAX CALCULATOR 2024 ===")
    print("(Values in US dollars)")
    print()
    
    # Get user input
    try:
        monthly_salary = float(input("Enter monthly salary: $"))
        deductible_expenses = float(input("Enter annual deductible expenses: $"))
    except ValueError:
        print("Invalid input. Please enter valid numbers.")
        return
    
    # Use the library to calculate all values
    annual_salary = monthly_salary * 12
    tax_base = calculate_tax_base(monthly_salary, deductible_expenses)
    annual_tax = calculate_annual_tax(tax_base)
    monthly_tax = calculate_monthly_tax(monthly_salary, deductible_expenses)
    
    # Display results
    print("\n" + generate_report(monthly_salary, deductible_expenses))
    
    # Display tax brackets for reference
    display_tax_brackets()

if __name__ == "__main__":
    main()