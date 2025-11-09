
import sys
from model.tax_calculator import TaxCalculator
from view.console_view import TaxView

def start_app():
    model = TaxCalculator()
    view = TaxView()

    try:
        data = view.get_tax_data()

        annual_gross_income = data["monthly_salary"] * 12.0
        
        annual_taxable_base = model.calculate_annual_taxable_base(data["monthly_salary"]) 
        annual_tax_due = model.calculate_annual_income_tax(annual_taxable_base)
        
        tax_rebate_amount = model.calculate_tax_rebate(
            data["personal_expenses"], data["dependents"], annual_gross_income
        ) 

        final_annual_tax = annual_tax_due - tax_rebate_amount
        if final_annual_tax < 0:
            final_annual_tax = 0.0
        
        final_balance = final_annual_tax - data["annual_withholdings"]

        results = {
            **data,
            "annual_gross_income": annual_gross_income,
            "annual_taxable_base": annual_taxable_base,
            "annual_tax_due": annual_tax_due,
            "tax_rebate_amount": tax_rebate_amount,
            "final_annual_tax": final_annual_tax,
            "final_balance": final_balance
        }
        view.display_tax_report(results)

    except Exception as e:
        view.display_error(str(e))

if __name__ == "__main__":
    start_app()