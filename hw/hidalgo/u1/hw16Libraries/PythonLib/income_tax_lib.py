BASE_DISCOUNT_2025 = 798.31

TAX_RANGES_2025 = (
    (0,     12081,   0,     0.00),
    (12081, 15311,   0,     0.05),
    (15311, 19956, 161,     0.10),
    (19956, 26458, 626,     0.12),
    (26458, 35339,1406,     0.15),
    (35339, 47196,2748,     0.20),
    (47196, 63558,5120,     0.25),
    (63558, 84720,9211,     0.30),
    (84720,112963,15560,    0.35),
    (112963, float("inf"), 25445, 0.37)
)

def calculate_band_multiplier(dependents):
    options = [7, 9, 11, 14, 17]
    return options[min(dependents, 5)] if dependents > 0 else 7

def compute_tax_amount(base):
    for lower, upper, fixed, rate in TAX_RANGES_2025:
        if base <= upper:
            extra = (base - lower) * rate
            return fixed + extra
    return 0

def compute_deduction(expenses, dependents):
    cap = calculate_band_multiplier(dependents) * BASE_DISCOUNT_2025
    valid_expense = expenses if expenses < cap else cap
    return valid_expense * 0.18

def get_net_balance(income, iess, expenses, dependents, paid_tax):
    taxable = income - iess
    taxable = taxable if taxable > 0 else 0

    tax = compute_tax_amount(taxable)
    deduction = compute_deduction(expenses, dependents)

    return tax - deduction - paid_tax
