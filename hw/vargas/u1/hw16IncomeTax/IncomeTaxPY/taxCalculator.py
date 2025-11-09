from incomeTaxCalculation import IncomeTaxCalculation

def Main():
    print("-----Welcome to Income Tax Calculation-----")
    
    try:
        MonthlySalary = float(input("Enter your monthly salary: "))
        AnnualPersonalExpenses = float(input("Enter your annual personal expenses: "))
        NumberOfFamilyDeficits = int(input("Enter your number of family deficits: "))
        
        IncomeTax = IncomeTaxCalculation(
            MonthlySalary, 
            AnnualPersonalExpenses, 
            NumberOfFamilyDeficits
        )
        
        print(f"Your Tax to pay this year is: {IncomeTax:.2f}")

    except ValueError:
        print("Error: Please enter valid numbers.")
    except Exception as E:
        print(f"An unexpected error occurred: {E}")

if __name__ == "__main__":
    Main()