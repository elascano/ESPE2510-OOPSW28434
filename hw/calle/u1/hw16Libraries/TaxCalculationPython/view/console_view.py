import sys

class TaxView:
    
    def get_tax_data(self):
        print("\n=============================================")
        print("  INCOME TAX CALCULATION - ECUADOR")
        print("=============================================")
        
        try:
            print("\n ANNUAL INCOME DATA")
            print("---------------------------------------------")
            
            monthly_salary = float(input("  Enter Gross Monthly Salary (USD): "))
            personal_expenses = float(input("  Enter Total Projected Personal Expenses: "))
            dependents = int(input("  Enter Number of Dependents (Max 5): "))
            annual_withholdings = float(input("  Enter Annual Income Tax Withheld (Paid): "))
            
            return {
                "monthly_salary": monthly_salary,
                "personal_expenses": personal_expenses,
                "dependents": dependents,
                "annual_withholdings": annual_withholdings
            }
        except ValueError:
            raise ValueError("Invalid input. Please ensure all fields are numeric.")
        except Exception as e:
            raise Exception(f"An unexpected error occurred during input: {e}")

    def display_tax_report(self, results):
        
      

        final_balance = results['final_balance']
        
        if final_balance > 0:
            print(f"**TAX PENDING TO PAY:** ${final_balance:,.2f}")
        elif final_balance < 0:
            print(f"**BALANCE IN FAVOR (REFUND):** ${abs(final_balance):,.2f}")
        else:
            print("**NO BALANCE DUE**")
        
        print("=============================================")

    def display_error(self, message):
        sys.stderr.write(f"\n[ERROR] {message}\n")