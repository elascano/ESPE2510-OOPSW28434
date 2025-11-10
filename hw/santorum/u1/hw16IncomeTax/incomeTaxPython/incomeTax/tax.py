class Tax:
    def __init__(self, monthly_salary, deductible_expenses):
        self.monthly_salary = monthly_salary
        self.deductible_expenses = deductible_expenses

    def calculate_tax(self):
        annual_income = self.monthly_salary * 12
        taxable_base = max(0, annual_income - self.deductible_expenses)

        # Table SRI 2025
        tax_table = [
            (0, 12081, 0, 0),
            (12081, 15387, 0, 0.05),
            (15387, 19978, 165, 0.10),
            (19978, 26422, 624, 0.12),
            (26422, 34770, 1398, 0.15),
            (34770, 46089, 2650, 0.20),
            (46089, 61359, 4914, 0.25),
            (61359, 81817, 8731, 0.30),
            (81817, 108810, 14869, 0.35),
            (108810, float('inf'), 24316, 0.37),
        ]

        for row in tax_table:
            if row[0] <= taxable_base < row[1]:
                base_tax, rate, fraction_min = row[2], row[3], row[0]
                break

        annual_tax = base_tax + (taxable_base - fraction_min) * rate
        annual_tax = max(0, annual_tax)
        monthly_tax = annual_tax / 12

        return {
            "annual_income": annual_income,
            "deductible_expenses": self.deductible_expenses,
            "taxable_base": taxable_base,
            "annual_tax": annual_tax,
            "monthly_tax": monthly_tax
        }
