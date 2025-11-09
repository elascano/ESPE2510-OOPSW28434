from income_tax import IncomeTax

class IncomeTaxCalculator:
    
    @staticmethod
    def get_validated_input(prompt):
        """Obtener entrada del usuario"""
        try:
            return input(prompt)
        except KeyboardInterrupt:
            print("\nProgram interrupted by user.")
            exit()
    
    @staticmethod
    def main():
        print("=" * 50)
        print("        INCOME TAX CALCULATOR")
        print("=" * 50)
        
        try:
            salary_input = IncomeTaxCalculator.get_validated_input("Enter your monthly salary: $")
            expenses_input = IncomeTaxCalculator.get_validated_input("Enter your monthly expenses: $")
            sector_input = IncomeTaxCalculator.get_validated_input("Employment sector (1=Public, 2=Private): ")
            
            monthly_salary = float(salary_input)
            monthly_expenses = float(expenses_input)
            institutional_sector = int(sector_input)
            
            IncomeTax.show_tax_results(monthly_salary, institutional_sector, monthly_expenses)
            
        except ValueError as error:
            print(f"ERROR: Invalid input format - {error}")
        except Exception as error:
            print(f"ERROR: {error}")

if __name__ == "__main__":
    IncomeTaxCalculator.main()