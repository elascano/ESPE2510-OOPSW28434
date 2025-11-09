def CalculateTaxableBase(MonthlySalary: float) -> float:
    AnnualIncome = MonthlySalary * 12
    AnnualIessContribution = AnnualIncome * 0.0945
    return AnnualIncome - AnnualIessContribution

def GetMaximumDiscount(NumberOfFamilyDeficits: int) -> float:
    if NumberOfFamilyDeficits == 0:
        return 1005.87
    elif NumberOfFamilyDeficits == 1:
        return 1293.26
    elif NumberOfFamilyDeficits == 2:
        return 1580.65
    elif NumberOfFamilyDeficits == 3:
        return 2011.74
    elif NumberOfFamilyDeficits == 4:
        return 2442.83
    else:
        return 2873.92

def CalculateFinalDiscount(AnnualPersonalExpenses: float, NumberOfFamilyDeficits: int) -> float:
    CalculatedDiscount = AnnualPersonalExpenses * 0.18
    MaximumDiscount = GetMaximumDiscount(NumberOfFamilyDeficits)
    return min(CalculatedDiscount, MaximumDiscount)

def CalculateTaxDue(TaxBase: float) -> float:
    BasicFraction = 0.0
    BasicTax = 0.0
    PercentageSurplus = 0.0

    if TaxBase <= 12081:
        BasicFraction = 0
        BasicTax = 0
        PercentageSurplus = 0.0
    elif TaxBase <= 15387:
        BasicFraction = 12081
        BasicTax = 0
        PercentageSurplus = 0.05
    elif TaxBase <= 19978:
        BasicFraction = 15387
        BasicTax = 165
        PercentageSurplus = 0.10
    elif TaxBase <= 26422:
        BasicFraction = 19978
        BasicTax = 624
        PercentageSurplus = 0.12
    elif TaxBase <= 34770:
        BasicFraction = 26422
        BasicTax = 1398
        PercentageSurplus = 0.15
    elif TaxBase <= 46089:
        BasicFraction = 34770
        BasicTax = 2650
        PercentageSurplus = 0.20
    elif TaxBase <= 61359:
        BasicFraction = 46089
        BasicTax = 4914
        PercentageSurplus = 0.25
    elif TaxBase <= 81817:
        BasicFraction = 61359
        BasicTax = 8731
        PercentageSurplus = 0.30
    elif TaxBase <= 108810:
        BasicFraction = 81817
        BasicTax = 14869
        PercentageSurplus = 0.35
    else:
        BasicFraction = 108810
        BasicTax = 24316
        PercentageSurplus = 0.37

    Surplus = TaxBase - BasicFraction
    TaxDue = BasicTax + (Surplus * PercentageSurplus)
    return TaxDue


def IncomeTaxCalculation(MonthlySalary: float, AnnualPersonalExpenses: float, NumberOfFamilyDeficits: int) -> float:
    
    TaxBase = CalculateTaxableBase(MonthlySalary)
    TaxDue = CalculateTaxDue(TaxBase)

    if TaxDue == 0.0:
        return 0.0

    FinalDiscount = CalculateFinalDiscount(AnnualPersonalExpenses, NumberOfFamilyDeficits)
    TaxToPay = TaxDue - FinalDiscount

    return max(0.0, TaxToPay)