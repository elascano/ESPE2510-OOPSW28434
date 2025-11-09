class IncomeTaxCalculator:
    def __init__(self, monthlyIncome: float, annualDeductions: float):
        self.monthlyIncome = monthlyIncome
        self.annualDeductions = annualDeductions

    def calculateAnnualTax(self) -> float:

        annualIncome = self.monthlyIncome * 12
        taxableBase = annualIncome - self.annualDeductions
        tax = 0.0

        if taxableBase <= 11902:
            tax = 0
        elif taxableBase <= 15159:
            tax = (taxableBase - 11902) * 0.05
        elif taxableBase <= 19682:
            tax = 163 + (taxableBase - 15159) * 0.10
        elif taxableBase <= 26031:
            tax = 615 + (taxableBase - 19682) * 0.12
        elif taxableBase <= 34255:
            tax = 1377 + (taxableBase - 26031) * 0.15
        elif taxableBase <= 45407:
            tax = 2611 + (taxableBase - 34255) * 0.20
        elif taxableBase <= 60450:
            tax = 4841 + (taxableBase - 45407) * 0.25
        elif taxableBase <= 80605:
            tax = 8602 + (taxableBase - 60450) * 0.30
        elif taxableBase <= 107199:
            tax = 14648 + (taxableBase - 80605) * 0.35
        else:
            tax = 23956 + (taxableBase - 107199) * 0.37  
            

        return max(tax, 0)

    def showSummary(self):

        annualTax = self.calculateAnnualTax()
        print("\n------------------------------------------")
        print(f"Monthly income: ${self.monthlyIncome:,.2f}")
        print(f"Annual deductions: ${self.annualDeductions:,.2f}")
        print(f"Estimated annual income tax: ${annualTax:,.2f}")
        print("------------------------------------------")
