# Author: Bryan Gudino, KNOWLEDGE ENCAPSULATE, @ESPE

class CalculationIncomeTax:
    CBF_JANUARY_2025 = 798.31

    # Tabla de tramos de impuesto SRI 2025
    TABLE_TRAMOS_2025 = [
        (0, 12081, 0, 0.00),
        (12081, 15311, 0, 0.05),
        (15311, 19956, 161, 0.10),
        (19956, 26458, 626, 0.12),
        (26458, 35339, 1406, 0.15),
        (35339, 47196, 2748, 0.20),
        (47196, 63558, 5120, 0.25),
        (63558, 84720, 9211, 0.30),
        (84720, 112963, 15560, 0.35),
        (112963, float("inf"), 25445, 0.37)
    ]

    @staticmethod
    def final_balance(gross_income, iees_contribution, personal_expenses, family_dependents, withholding):
        taxable_base = max(0, gross_income - iees_contribution)
        tax_due = CalculationIncomeTax.tax_due(taxable_base)
        discount_expenses = CalculationIncomeTax.discount(personal_expenses, family_dependents)
        final_balance = tax_due - discount_expenses - withholding
        return final_balance

    @staticmethod
    def tax_due(taxable_base):
        for tramo in CalculationIncomeTax.TABLE_TRAMOS_2025:
            lower, upper, basic_tax, percent = tramo
            if taxable_base <= upper:
                surplus = taxable_base - lower
                return basic_tax + surplus * percent
        return 0

    @staticmethod
    def discount(personal_expenses, family_dependents):
        num_band = CalculationIncomeTax.get_tax_band(family_dependents)
        max_spending = num_band * CalculationIncomeTax.CBF_JANUARY_2025
        base_discount = min(personal_expenses, max_spending)
        return base_discount * 0.18

    @staticmethod
    def get_tax_band(dependents):
        if dependents <= 0:
            return 7
        elif dependents == 1:
            return 9
        elif dependents == 2:
            return 11
        elif dependents == 3:
            return 14
        elif dependents == 4:
            return 17
        else:
            return 20
