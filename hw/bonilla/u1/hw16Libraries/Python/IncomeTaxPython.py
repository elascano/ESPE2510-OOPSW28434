def calculate_tax(monthly_salary, monthly_expenses):
    annual_income = monthly_salary * 12
    deductible = monthly_expenses * 12
    taxable_income = annual_income - deductible

    if taxable_income <= 0:
        return 0

    if taxable_income <= 12000:
        tax = taxable_income * 0.05
    elif taxable_income <= 25000:
        tax = 12000 * 0.05 + (taxable_income - 12000) * 0.10
    elif taxable_income <= 40000:
        tax = 12000 * 0.05 + 13000 * 0.10 + (taxable_income - 25000) * 0.15
    else:
        tax = 12000 * 0.05 + 13000 * 0.10 + 15000 * 0.15 + (taxable_income - 40000) * 0.20

    return tax
