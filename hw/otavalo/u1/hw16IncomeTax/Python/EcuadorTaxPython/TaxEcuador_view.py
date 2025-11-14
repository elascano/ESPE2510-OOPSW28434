from LibraryTaxCalculator import IncomeTaxCalculator

def main():
    print("=== Ecuador Income Tax Calculator 2024 ===")

    while True:
        monthlyIncome = float(input("\nEnter your MONTHLY income: ").replace(",", "."))
        annualDeductions = float(input("Enter your ANNUAL deductions: ").replace(",", "."))

        calculator = IncomeTaxCalculator(monthlyIncome, annualDeductions)
        calculator.showSummary()

        option = input("\nDo you want to calculate again? (yes/no): ").strip().lower()
        if option == "no" or option == "n":
            print("\n Bye Bye")
            break

if __name__ == "__main__":
    main()
