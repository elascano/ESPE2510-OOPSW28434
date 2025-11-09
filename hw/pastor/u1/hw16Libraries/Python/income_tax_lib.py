CBD_JANUARY_2025 = 798.31
TABLE_TRAMOS_2025 = [
    {"lower_limit": 0, "upper_limit": 12081, "basic_tax": 0, "excess_percentage": 0.00},
    {"lower_limit": 12081, "upper_limit": 15311, "basic_tax": 0, "excess_percentage": 0.05},
    {"lower_limit": 15311, "upper_limit": 19956, "basic_tax": 161, "excess_percentage": 0.10},
    {"lower_limit": 19956, "upper_limit": 26458, "basic_tax": 626, "excess_percentage": 0.12},
    {"lower_limit": 26458, "upper_limit": 35339, "basic_tax": 1406, "excess_percentage": 0.15},
    {"lower_limit": 35339, "upper_limit": 47196, "basic_tax": 2748, "excess_percentage": 0.20},
    {"lower_limit": 47196, "upper_limit": 63558, "basic_tax": 5120, "excess_percentage": 0.25},
    {"lower_limit": 63558, "upper_limit": 84720, "basic_tax": 9211, "excess_percentage": 0.30},
    {"lower_limit": 84720, "upper_limit": 112963, "basic_tax": 15560, "excess_percentage": 0.35},
    {"lower_limit": 112963, "upper_limit": float('inf'), "basic_tax": 25445, "excess_percentage": 0.37}
]

def _get_tax_band(depents: int) -> int:
    if depents <= 0:
        return 7
    elif depents == 1:
        return 9
    elif depents == 2:
        return 11
    elif depents == 3:
        return 14
    elif depents == 4:
        return 17
    else:
        return 20
    
def _tax_due(taxable_base: float) -> float:
    for tramo in TABLE_TRAMOS_2025:
        if taxable_base <= tramo["upper_limit"]:
            surplus = taxable_base - tramo["lower_limit"]
            surplusTax = surplus * tramo["excess_percentage"]
            return tramo["basic_tax"] + surplusTax
    return 0

def _discount(personal_expenses: float, family_depents: int) -> float:
    numTaxBand = _get_tax_band(family_depents)
    maximun_spending_limit = numTaxBand * CBD_JANUARY_2025
    base_discount = min(personal_expenses, maximun_spending_limit)
    return base_discount * 0.18

def _final_balance(gross_income: float, iees_contribution: float, personal_expenses: float, family_depents: int, withholding: float) -> float:
    taxable_base = max(0, gross_income - iees_contribution)
    tax_due = _tax_due(taxable_base)
    discount_expenses = _discount(personal_expenses, family_depents)
    final_balance = tax_due - discount_expenses - withholding
    return final_balance

