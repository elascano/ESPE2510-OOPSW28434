import sys
sys.path.append('.') 
from IncomeTaxOperations.Operation import Operation 

def format_currency(amount: float) -> str:
    """Formatea el número a moneda con dos decimales."""
    return f"${amount:,.2f}"

def main():
    print("--- WELCOME TO THE INCOME TAX CALCULATOR BY MARYURI ---")
    
    try:
        monthly_salary = float(input("Enter your average monthly salary: "))
        annual_spent_deductible = float(input("Enter your total Annual Personal Expenses: "))
        dependents = int(input("Enter Number of Dependents: "))
        annual_withholdings = float(input("Enter Annual Withholdings (already paid): "))
        
    except ValueError:
        print("\nERROR: Asegúrese de ingresar solo números válidos. Saliendo del programa.")
        sys.exit(1)
    
    annual_salary = monthly_salary * 12.0
    taxable_base = Operation.calculate_annual_taxable_base(annual_salary, annual_spent_deductible, dependents)
    annual_tax_required = Operation.calculate_annual_income_tax(taxable_base)
    balance = annual_withholdings - annual_tax_required
  
    print("\n----------------------------------------------")
    print("Annual Tax REQUIRED: " + format_currency(annual_tax_required))
    print("Annual Withholdings PAID: " + format_currency(annual_withholdings))
    print("----------------------------------------------")

    if balance >= 0.01:
        print("RESULT: Balance in favor (Refund): " + format_currency(balance))
    elif balance <= -0.01:
        print("RESULT: Tax to pay this year is: " + format_currency(abs(balance)))
    else:
        print("RESULT: Final balance is zero.")
        
    print("==============================================")

if __name__ == "__main__":
    main()