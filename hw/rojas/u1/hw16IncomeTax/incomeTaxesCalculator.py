#author Josue Rojas
#UseofLibrary
from incomeTaxesCalculatorlib import IncomeTaxEC

def run():
    print("----- Welcome to Income Tax Calculation -----")
    calc = IncomeTaxEC()

    while True:
        try:
            monthly = float(input("Enter your monthly salary:\n").strip())
            spent = float(input("Enter your annual personal expenses:\n").strip())
            deps = int(input("Enter your number of family dependents:\n").strip())

            result = calc.compute(monthly_salary=monthly, annual_spent=spent, dependents=deps)

            print(f"\nYour Tax to pay this year is: {round(result.annual_tax, 1)}")

        except Exception as e:
            print("Input error:", e)

        again = input("Would you like to calculate again? (s/n): ").strip().lower()
        if again != "s":
            print("Goodbye!")
            break

if __name__ == "__main__":
    run()
