import income_tax_lib as ir


def _reed_float(message: str) -> float:
    while True:
        try:
            return float(input(message))
        except ValueError:
            print("Incorrect data, please enter a number (e.g 1254.66)")

def _reed_int(message: str) -> int:
    while True:
        try:
            return float(input(message))
        except ValueError:
            print("Incorrect data, please enter a number (e.g 16)")

def main():
    print("----- WELCOME TO THE INCOME TAX ECUADOR 2025 BY MATHEWS PASTOR -----")

    mounthy_salary = _reed_float("Enter your average monthly salary: ")
    annual_personal_expenses = _reed_float("Enter your total Annual Personal Expenses: ")
    depents = _reed_int("Enter Number of Dependents: ")
    annual_withholding = _reed_float("Enter Annual Withholdings (already paid): ")
    
    annual_gross_income = mounthy_salary * 12
    annual_iees_contributions = annual_gross_income * 0.0945

    balance = ir._final_balance(annual_gross_income, annual_iees_contributions, annual_personal_expenses, depents, annual_withholding)
    if balance > 0:
        print(f"RESULT: Tax Payable: ${balance}")
    else:
        print(f"RESULT: Balance in favor: ${abs(balance)}")
    
main()