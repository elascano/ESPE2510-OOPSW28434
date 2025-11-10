import sys
import os

# Agregar la ruta del directorio padre
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from calculation.income_tax import IncomeTax

class IncomeTaxCalculator:
    @staticmethod
    def main():
        print("        INCOME TAX CALCULATOR BY KEVIN CHALAN")
        
        monthly_salary = IncomeTax.get_validated_double("your salary in DOLLARS: ")
        monthly_expenses = IncomeTax.get_validated_double("your monthly expenses: ")
        institutional_sector = IncomeTax.get_validated_sector()
        
        IncomeTax.show_tax_results(monthly_salary, institutional_sector, monthly_expenses)

if __name__ == "__main__":
    IncomeTaxCalculator.main()