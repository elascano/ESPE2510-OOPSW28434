class IncomeTax:
    
    @staticmethod
    def contribution_iess(monthly_salary, institutional_sector):
        if institutional_sector == 2 and monthly_salary > 2300:
            base_calculo = 2300
        else:
            base_calculo = monthly_salary

        general_insurance = base_calculo * 0.0945
        unemployment_insurance = base_calculo * 0.02
        total_contribution_iess = general_insurance + unemployment_insurance

        return total_contribution_iess

    @staticmethod
    def calculate_tax_without_deductions(annual_net_salary_before_tax):
        tax = 0

        if annual_net_salary_before_tax <= 11902:
            tax = 0
        elif annual_net_salary_before_tax <= 15159:
            tax = (annual_net_salary_before_tax - 11902) * 0.05
        elif annual_net_salary_before_tax <= 19682:
            tax = 163 + (annual_net_salary_before_tax - 15159) * 0.10
        elif annual_net_salary_before_tax <= 26031:
            tax = 615 + (annual_net_salary_before_tax - 19682) * 0.12
        elif annual_net_salary_before_tax <= 34255:
            tax = 1377 + (annual_net_salary_before_tax - 26031) * 0.15
        elif annual_net_salary_before_tax <= 45407:
            tax = 2611 + (annual_net_salary_before_tax - 34255) * 0.20
        elif annual_net_salary_before_tax <= 60450:
            tax = 4841 + (annual_net_salary_before_tax - 45407) * 0.25
        elif annual_net_salary_before_tax <= 80605:
            tax = 8602 + (annual_net_salary_before_tax - 60450) * 0.30
        elif annual_net_salary_before_tax <= 107199:
            tax = 14648 + (annual_net_salary_before_tax - 80605) * 0.35
        else:
            tax = 23956 + (annual_net_salary_before_tax - 107199) * 0.37

        return tax
    
    @staticmethod
    def get_marginal_tax_rate(income):
        if income <= 11902:
            return 0.00
        elif income <= 15159:
            return 0.05
        elif income <= 19682:
            return 0.10
        elif income <= 26031:
            return 0.12
        elif income <= 34255:
            return 0.15
        elif income <= 45407:
            return 0.20
        elif income <= 60450:
            return 0.25
        elif income <= 80605:
            return 0.30
        elif income <= 107199:
            return 0.35
        else:
            return 0.37

    @staticmethod
    def calculate_rebaja(annual_net_salary_before_tax, monthly_expenses):
        annual_expenses = monthly_expenses * 12
        deductible_expenses = min(annual_expenses, 5344)
        marginal_rate = IncomeTax.get_marginal_tax_rate(annual_net_salary_before_tax)

        if annual_net_salary_before_tax > 26031 and annual_net_salary_before_tax <= 34255:
            base_for_rebaja = 6423.53
        else:
            base_for_rebaja = deductible_expenses

        rebate = base_for_rebaja * marginal_rate
        return rebate

    @staticmethod
    def calculate_income_tax(annual_net_salary_before_tax, monthly_expenses):
        tax_without_deductions = IncomeTax.calculate_tax_without_deductions(annual_net_salary_before_tax)
        rebate = IncomeTax.calculate_rebaja(annual_net_salary_before_tax, monthly_expenses)
        final_tax = tax_without_deductions - rebate
        
        return max(final_tax, 0)

    @staticmethod
    def show_tax_results(monthly_salary, institutional_sector, monthly_expenses):
        annual_gross_salary = monthly_salary * 12
        monthly_iess = IncomeTax.contribution_iess(monthly_salary, institutional_sector)
        annual_iess = monthly_iess * 12
        annual_net_before_tax = annual_gross_salary - annual_iess

        income_tax = IncomeTax.calculate_income_tax(annual_net_before_tax, monthly_expenses)
        final_net_salary = annual_net_before_tax - income_tax

        print("INCOME TAX RESULTS")
        print(f"YOUR ANNUAL INCOME TAX IS: ${income_tax:.2f}")
        print(f"Your annual net salary is: ${final_net_salary:.2f}")

    @staticmethod
    def get_validated_double(message):
        while True:
            try:
                value = float(input(message))
                if value < 0:
                    print("ERROR: Value cannot Be Negative.")
                elif value == 0:
                    print("ERROR: Value cannot Be Zero.")
                elif value > 20000:
                    print("ERROR: exceed $20000")
                else:
                    return value
            except ValueError:
                print("Enter a valid number.")

    @staticmethod
    def get_validated_sector():
        while True:
            try:
                sector = int(input("sector 1=Public, 2=Private: "))
                if sector == 1 or sector == 2:
                    return sector
                else:
                    print("must be 1 (Public) or 2 (Private).")
            except ValueError:
                print("Enter 1 or 2.")