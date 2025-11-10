"""
Library for calculating income tax according to 2024 tax brackets
"""

class TaxBracket:
    """Class to represent each tax bracket"""
    def __init__(self, basic_fraction, excess_up_to, basic_fraction_tax, excess_percentage):
        self.basic_fraction = basic_fraction
        self.excess_up_to = excess_up_to
        self.basic_fraction_tax = basic_fraction_tax
        self.excess_percentage = excess_percentage

# Tax brackets according to the image
TAX_BRACKETS_2024 = [
    TaxBracket(0, 11902, 0, 0),
    TaxBracket(11902, 15159, 0, 5),
    TaxBracket(15159, 19682, 163, 10),
    TaxBracket(19682, 26031, 615, 12),
    TaxBracket(26031, 34255, 1377, 15),
    TaxBracket(34255, 45407, 2611, 20),
    TaxBracket(45407, 60450, 4841, 25),
    TaxBracket(60450, 80605, 8602, 30),
    TaxBracket(80605, 107199, 14648, 35),
    TaxBracket(107199, float('inf'), 23956, 37)
]

def calculate_annual_tax(annual_salary):
    """
    Calculates annual tax based on annual salary
    :param annual_salary: Annual salary in dollars
    :return: Calculated annual tax
    """
    if annual_salary <= 0:
        return 0
    
    for i in range(len(TAX_BRACKETS_2024) - 1, -1, -1):
        bracket = TAX_BRACKETS_2024[i]
        
        if annual_salary > bracket.basic_fraction:
            excess = annual_salary - bracket.basic_fraction
            excess_tax = excess * (bracket.excess_percentage / 100)
            return bracket.basic_fraction_tax + excess_tax
    
    return 0

def calculate_monthly_tax(monthly_salary, deductible_expenses):
    """
    Calculates monthly tax considering deductible expenses
    :param monthly_salary: Monthly salary in dollars
    :param deductible_expenses: Annual deductible expenses
    :return: Monthly tax to pay
    """
    annual_salary = monthly_salary * 12
    tax_base = max(0, annual_salary - deductible_expenses)
    annual_tax = calculate_annual_tax(tax_base)
    return annual_tax / 12

def calculate_tax_base(monthly_salary, deductible_expenses):
    """
    Calculates the annual tax base
    :param monthly_salary: Monthly salary
    :param deductible_expenses: Annual deductible expenses
    :return: Tax base for tax calculation
    """
    annual_salary = monthly_salary * 12
    return max(0, annual_salary - deductible_expenses)

def get_tax_bracket(annual_salary):
    """
    Gets the tax bracket for a given annual salary
    :param annual_salary: Annual salary
    :return: Corresponding tax bracket
    """
    for bracket in TAX_BRACKETS_2024:
        if annual_salary <= bracket.excess_up_to:
            return bracket
    return TAX_BRACKETS_2024[-1]

def generate_report(monthly_salary, deductible_expenses):
    """
    Generates a detailed tax calculation report
    :param monthly_salary: Monthly salary
    :param deductible_expenses: Annual deductible expenses
    :return: Formatted report string
    """
    annual_salary = monthly_salary * 12
    tax_base = calculate_tax_base(monthly_salary, deductible_expenses)
    annual_tax = calculate_annual_tax(tax_base)
    monthly_tax = annual_tax / 12
    bracket = get_tax_bracket(tax_base)
    
    return f"""=== INCOME TAX REPORT 2024 ===
Monthly salary: ${monthly_salary:,.2f}
Annual salary: ${annual_salary:,.2f}
Deductible expenses: ${deductible_expenses:,.2f}
Tax base: ${tax_base:,.2f}
Tax bracket: Up to ${bracket.excess_up_to:,.0f}
Annual tax: ${annual_tax:,.2f}
Monthly tax: ${monthly_tax:,.2f}
Marginal percentage: {bracket.excess_percentage:.1f}%"""

def display_tax_brackets():
    """Displays all tax brackets for reference"""
    print("\n=== 2024 TAX BRACKETS ===")
    print("Basic Fraction\tExcess Up To\tBasic Tax\tExcess %")
    for bracket in TAX_BRACKETS_2024:
        print(f"${bracket.basic_fraction:.0f}\t\t${bracket.excess_up_to:.0f}\t\t${bracket.basic_fraction_tax:.0f}\t\t{bracket.excess_percentage:.0f}%")