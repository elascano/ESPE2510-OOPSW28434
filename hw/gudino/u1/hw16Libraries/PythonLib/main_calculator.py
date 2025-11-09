# Author: Bryan Gudino, KNOWLEDGE ENCAPSULATE, @ESPE

from calculation_income_tax import CalculationIncomeTax

def read_float(message):
    while True:
        try:
            return float(input(message))
        except ValueError:
            print("Incorrect data, please enter a number (e.g 1254.66)")

def read_int(message):
    while True:
        try:
            return int(input(message))
        except ValueError:
            print("Incorrect data, please enter a number (e.g 1)")

def main():
    print("ECUADOR TAX CALCULATOR 2025")
    monthly_salary = read_float("Enter your average monthly salary: ")
    annual_personal_expenses = read_float("Enter your total Annual Personal Expenses: ")
    dependents = read_int("Enter Number of Dependents: ")
    annual_withholding = read_float("Enter Annual Withholdings already paid: ")

    annual_gross_income = monthly_salary * 12
    annual_iees = annual_gross_income * 0.0945

    balance = CalculationIncomeTax.final_balance(
        annual_gross_income, 
        annual_iees, 
        annual_personal_expenses, 
        dependents, 
        annual_withholding
    )

    if balance > 0:
        print(f"RESULT: Tax Payable: ${balance:,.2f}")
    else:
        print(f"Balance in favor: ${abs(balance):,.2f}")

if __name__ == "__main__":
    main()
