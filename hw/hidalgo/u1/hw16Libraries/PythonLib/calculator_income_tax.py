import income_tax_lib as ir

def ask_number(text, whole=False):
    while True:
        try:
            return int(input(text)) if whole else float(input(text))
        except ValueError:
            print("Please enter a valid number!")

def run_calculator():
    print("\n======= INCOME TAX ECUADOR 2025 =======\n")

    monthly = ask_number("Enter monthly salary: $")
    expenses = ask_number("Enter annual personal expenses: $")
    dependents = ask_number("Number of dependents: ", True)
    withheld = ask_number("Tax already withheld: $")

    annual_income = monthly * 12
    iess = annual_income * 0.0945

    result = ir.get_net_balance(annual_income, iess, expenses, dependents, withheld)

    if result > 0:
        print(f"\n Tax to pay: ${result:.2f}")
    else:
        print(f"\n Balance in your favor: ${abs(result):.2f}")

if __name__ == "__main__":
    run_calculator()
