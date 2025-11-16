class Operation:
    """Clase que implementa la lógica fiscal para el Impuesto a la Renta."""
    FBE = 11902.00
    BASE_UVC = 5355.90 
    ANNUAL_TAX_BRACKETS = [
        (0.00, 0.00, 0.00),       
        (11902.00, 0.00, 0.05),   
        (15159.00, 163.00, 0.10), 
        (19682.00, 615.00, 0.12), 
        (26031.00, 1377.00, 0.15),
        (34255.00, 2611.00, 0.20),
        (45407.00, 4841.00, 0.25),
        (60450.00, 8602.00, 0.30),
        (80605.00, 14648.00, 0.35),
        (107199.00, 23956.00, 0.37)
    ]

    @staticmethod
    def calculate_annual_taxable_base(annual_salary: float, annual_spent_deductible: float, dependents: int) -> float:
        
        max_deductible_expenses = Operation.BASE_UVC * (1 + 0.5 * dependents)
        actual_deductible_expenses = min(annual_spent_deductible, max_deductible_expenses)
        income_after_expenses = annual_salary - actual_deductible_expenses
        taxable_base = income_after_expenses - Operation.FBE
        
        return max(0.0, taxable_base)

    @staticmethod
    def calculate_annual_income_tax(taxable_base: float) -> float:
        annual_tax = 0.0
        
        for i, bracket in enumerate(Operation.ANNUAL_TAX_BRACKETS):
            basic_fraction, fixed_tax, marginal_rate = bracket
            
            if taxable_base >= basic_fraction:
                up_to = Operation.ANNUAL_TAX_BRACKETS[i + 1][0] if i < len(Operation.ANNUAL_TAX_BRACKETS) - 1 else float('inf')

                annual_tax = fixed_tax
                amount_taxed_in_bracket = taxable_base - basic_fraction
                
                if taxable_base < up_to:
                    amount_taxed_in_bracket = min(amount_taxed_in_bracket, up_to - basic_fraction)
                
                annual_tax += amount_taxed_in_bracket * marginal_rate
                
                if taxable_base < up_to:
                    break
                    
        return annual_tax