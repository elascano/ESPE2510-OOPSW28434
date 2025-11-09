class TaxCalculator:

    TAX_TABLE_2024 = [
        (0, 11902, 0, 0.0),
        (11902, 15159, 0, 0.05),
        (15159, 19682, 163, 0.10),
        (19682, 26031, 615, 0.12),
        (26031, 34255, 1377, 0.15),
        (34255, 45407, 2611, 0.20),
        (45407, 60450, 4841, 0.25),
        (60450, 80605, 8602, 0.30),
        (80605, 107199, 14648, 0.35),
        (107199, float('inf'), 23956, 0.37)
    ]

    IESS_PERCENTAGE = 0.0945
    CANASTA_FAMILIAR_BASICA = 789.57
    MAX_DEPENDENTS = 5
    MIN_INCOME_FOR_REBATE = 24652.5

    def calculate_annual_taxable_base(self, monthly_salary):
        annual_income = monthly_salary * 12
        annual_iess_deduction = annual_income * self.IESS_PERCENTAGE
        return annual_income - annual_iess_deduction

    def calculate_annual_income_tax(self, taxable_base):
        if taxable_base <= self.TAX_TABLE_2024[0][1]:
            return 0.0

        annual_tax = 0.0
        
        for basic_fraction, excess_up_to, basic_tax, excess_tax_percentage in self.TAX_TABLE_2024:
            if taxable_base > basic_fraction and taxable_base <= excess_up_to:
                excess_fraction = taxable_base - basic_fraction
                excess_tax = excess_fraction * excess_tax_percentage
                annual_tax = basic_tax + excess_tax
                break 
        return annual_tax

    def calculate_tax_rebate(self, personal_expenses, dependents, annual_gross_income):
        
        dependent_factor = 1.0 + min(dependents, self.MAX_DEPENDENTS)
        spending_limit = self.CANASTA_FAMILIAR_BASICA * 7.0 * dependent_factor / 5.0
        
        max_spending_limit = 20000.0
        spending_limit = min(spending_limit, max_spending_limit)

        expenses_to_apply = min(personal_expenses, spending_limit)

        annual_limit = annual_gross_income
        rebate_percentage = 0.10
        
        if annual_limit <= self.MIN_INCOME_FOR_REBATE:
            rebate_percentage = 0.20
        
        max_rebate_amount = (self.CANASTA_FAMILIAR_BASICA * 7.0) * rebate_percentage

        calculated_rebate = expenses_to_apply * rebate_percentage 

        return min(calculated_rebate, max_rebate_amount)