class EcuadorTaxCalculator:
    @staticmethod
    def calculate_taxable_base(gross_annual_income, allowable_deductions):
        tax_base = gross_annual_income - allowable_deductions
        return tax_base

    @staticmethod
    def find_tax_bracket(tax_base):
        if 0 < tax_base < 12081:
            return "Bracket 1: Base = 0, Excess up to $12,081 your Basic Fraction Tax is $0 and you're free of taxes"
        elif 12081 <= tax_base < 15387:
            return "Bracket 2: Base = $12,081, Excess up to $15,387. Your basic fraction tax is $0, and your excess fraction tax rate is 5%."
        elif 15387 <= tax_base < 19978:
            return "Bracket 3: Base = $15,387, Excess up to $19,978. Your Basic Fraction Tax is $165 and your excess fraction tax rate is 10%."
        elif 19978 <= tax_base < 26422:
            return "Bracket 4: Base = $19,978, Excess up to $26,422. Your Basic Fraction Tax is $624 and your excess fraction tax rate is 12%."
        elif 26422 <= tax_base < 34770:
            return "Bracket 5: Base = $26,422, Excess up to $34,770. Your Basic Fraction Tax is $1,398 and your excess fraction tax rate is 15%."
        elif 34770 <= tax_base < 46089:
            return "Bracket 6: Base = $34,770, Excess up to $46,089. Your Basic Fraction Tax is $2,650 and your excess fraction tax rate is 20%."
        elif 46089 <= tax_base < 61359:
            return "Bracket 7: Base = $46,089, Excess up to $61,359. Your Basic Fraction Tax is $4,914 and your excess fraction tax rate is 25%."
        elif 61359 <= tax_base < 81817:
            return "Bracket 8: Base = $61,359, Excess up to $81,817. Your Basic Fraction Tax is $8,731 and your excess fraction tax rate is 30%."
        elif 81817 <= tax_base < 108810:
            return "Bracket 9: Base = $81,817, Excess up to $108,810. Your Basic Fraction Tax is $14,869 and your excess fraction tax rate is 35%."
        elif tax_base >= 108810:
            return "Excess over $108,810. Your basic fraction tax is $24,316 and your excess fraction tax rate is 37%."
        else:
            return "Tax base not within valid range"

    @staticmethod
    def calculate_total_tax(tax_base):
        if 0 < tax_base < 12081:
            return 0.0
        elif 12081 <= tax_base < 15387:
            return (tax_base - 12081) * 0.05 + 0
        elif 15387 <= tax_base < 19978:
            return (tax_base - 15387) * 0.10 + 165
        elif 19978 <= tax_base < 26422:
            return (tax_base - 19978) * 0.12 + 624
        elif 26422 <= tax_base < 34770:
            return (tax_base - 26422) * 0.15 + 1398
        elif 34770 <= tax_base < 46089:
            return (tax_base - 34770) * 0.20 + 2650
        elif 46089 <= tax_base < 61359:
            return (tax_base - 46089) * 0.25 + 4914
        elif 61359 <= tax_base < 81817:
            return (tax_base - 61359) * 0.30 + 8731
        elif 81817 <= tax_base < 108810:
            return (tax_base - 81817) * 0.35 + 14869
        elif tax_base >= 108810:
            return (tax_base - 108810) * 0.37 + 24316
        else:
            return 0
