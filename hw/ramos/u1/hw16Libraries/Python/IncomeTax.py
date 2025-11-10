class IncomeTax:

    def __init__(self):
        # Ecuador SRI Tax Table 2024 (in USD)
        self.brackets = [
            (0,        11902,   0,       0.00),
            (11902,    15159,   0,       0.05),
            (15159,    19682,   163,     0.10),
            (19682,    26031,   617,     0.12),
            (26031,    34255,   1377,    0.15),
            (34255,    45407,   2611,    0.20),
            (45407,    60450,   4841,    0.25),
            (60450,    80665,   8591,    0.30),
            (80665,   107199,   14466,   0.35),
            (107199,  float('inf'),  23956,  0.37)
        ]

    def calculate_annual_tax(self, annual_income):

        for bracket in self.brackets:
            lower, upper, base_tax, rate = bracket
            if annual_income <= upper:
                excess = annual_income - lower
                total_tax = base_tax + excess * rate
                return round(total_tax, 2)
        return 0.0

    def calculate_monthly_tax(self, monthly_salary, monthly_expenses):

        annual_income = monthly_salary * 12
        annual_expenses = monthly_expenses * 12
        taxable_base = max(0, annual_income - annual_expenses)

        annual_tax = self.calculate_annual_tax(taxable_base)
        monthly_tax = annual_tax / 12
        return round(monthly_tax, 2)

    def find_tax_bracket(self, annual_income):

        for lower, upper, _, rate in self.brackets:
            if annual_income <= upper:
                return f"Your income is in the range ${lower} - ${upper} with a {int(rate * 100)}% tax rate."
        return "Income exceeds all defined brackets."
