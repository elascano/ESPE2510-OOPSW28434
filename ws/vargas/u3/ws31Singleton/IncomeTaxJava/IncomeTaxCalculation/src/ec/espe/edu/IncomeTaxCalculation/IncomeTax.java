package ec.espe.edu.incometaxcalculation;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
public class IncomeTax {
    
    private static IncomeTax instance;

    private IncomeTax() {
    }

    public static IncomeTax getInstance() {
        if (instance == null) {
            instance = new IncomeTax();
        }
        return instance;
    }

    public double incomeTaxCalculation(float monthlySalary, double annualPersonalExpenses, int numberOfFamilyDeficits) {
        double taxBase;
        double taxDue;
        double finalDiscount;
        double taxtoPay;
        
        taxBase = calculateTaxableBase(monthlySalary);
        taxDue = calculateTaxDue(monthlySalary);
        
        if (taxDue == 0.0) {
            return 0.0;
        }
        finalDiscount = calculateFinalDiscount(annualPersonalExpenses, numberOfFamilyDeficits);
        taxtoPay = taxDue - finalDiscount;
        return Math.max(0.0, taxtoPay);
    }

    public float calculateTaxableBase(float monthlySalary) {
        float annualIncome;
        float annualIESSContribution;
        annualIncome = monthlySalary * 12;
        annualIESSContribution = monthlySalary * 0.0945F * 12;
        return annualIncome - annualIESSContribution;
    }

    public double calculateTaxDue(float monthlySalary) {
        double taxBase = calculateTaxableBase(monthlySalary);
        double basicFraction;
        double basicTax;
        double percentageSurplus;
        double surplus;
        double taxDue;
        
        if (taxBase <= 12081) {
            basicFraction = 0;
            basicTax = 0;
            percentageSurplus = 0;
        } else if (taxBase <= 15387) {
            basicFraction = 12081;
            basicTax = 0;
            percentageSurplus = 0.05;
        } else if (taxBase <= 19978) {
            basicFraction = 15387;
            basicTax = 165;
            percentageSurplus = 0.10;
        } else if (taxBase <= 26422) {
            basicFraction = 19978;
            basicTax = 624;
            percentageSurplus = 0.12;
        } else if (taxBase <= 34770) {
            basicFraction = 26422;
            basicTax = 1398;
            percentageSurplus = 0.15;
        } else if (taxBase <= 46089) {
            basicFraction = 34770;
            basicTax = 2650;
            percentageSurplus = 0.20;
        } else if (taxBase <= 61359) {
            basicFraction = 46089;
            basicTax = 4914;
            percentageSurplus = 0.25;
        } else if (taxBase <= 81817) {
            basicFraction = 61359;
            basicTax = 8731;
            percentageSurplus = 0.30;
        } else if (taxBase <= 108810) {
            basicFraction = 81817;
            basicTax = 14869;
            percentageSurplus = 0.35;
        } else {
            basicFraction = 108810;
            basicTax = 24316;
            percentageSurplus = 0.37;
        }
        
        surplus = taxBase - basicFraction;
        taxDue = basicTax + (surplus * percentageSurplus);

        return taxDue;
    }

    public double calculateFinalDiscount(double annualPersonalExpenses, int numberOfFamilyDeficits) {
        double calculatedDiscount;
        double maximumDiscount;
        calculatedDiscount = annualPersonalExpenses * 0.18;
        maximumDiscount = getMaximumDiscount(numberOfFamilyDeficits);

        return Math.min(calculatedDiscount, maximumDiscount);
    }

    public double getMaximumDiscount(int numberOfFamilyDeficits) {
        switch (numberOfFamilyDeficits) {
            case 0: return 1005.87;
            case 1: return 1293.26;
            case 2: return 1580.65;
            case 3: return 2011.74;
            case 4: return 2442.83;
            case 5: default: return 2873.92;
        }
    }
}