package ec.edu.espe.incometax.operations;

/**
 * 
 * @author Emily Calle
 */

public class TaxOperation {

    private static final double[][] TAX_TABLE_2024 = {
        {0, 11902, 0, 0.0},
        {11902, 15159, 0, 0.05},
        {15159, 19682, 163, 0.10},
        {19682, 26031, 615, 0.12},
        {26031, 34255, 1377, 0.15},
        {34255, 45407, 2611, 0.20},
        {45407, 60450, 4841, 0.25},
        {60450, 80605, 8602, 0.30},
        {80605, 107199, 14648, 0.35},
        {107199, Double.MAX_VALUE, 23956, 0.37}
    };

    private static final double IESS_PERCENTAGE = 0.0945;
    private static final double CANASTA_FAMILIAR_BASICA = 789.57;
    private static final int MAX_DEPENDENTS = 5;
    private static final double MIN_INCOME_FOR_REBATE = 24652.5;

    public double calculateAnnualTaxableBase(double monthlySalary) {
        double annualIncome = monthlySalary * 12;
        double annualIessDeduction = annualIncome * IESS_PERCENTAGE; 
        return annualIncome - annualIessDeduction;
    }

    public double calculateAnnualIncomeTax(double taxableBase) {
        if (taxableBase <= TAX_TABLE_2024[0][1]) {
            return 0.0;
        }

        double annualTax = 0.0;
        
        for (double[] row : TAX_TABLE_2024) {
            double basicFraction = row[0];
            double excessUpTo = row[1];
            double basicTax = row[2];
            double excessTaxPercentage = row[3];

            if (taxableBase > basicFraction && taxableBase <= excessUpTo) {
                double excessFraction = taxableBase - basicFraction;
                double excessTax = excessFraction * excessTaxPercentage;
                annualTax = basicTax + excessTax;
                break; 
            }
        }
        return annualTax;
    }

    public double calculateTaxRebate(double personalExpenses, int dependents, double annualGrossIncome) {
        
        double spendingLimit = CANASTA_FAMILIAR_BASICA * 7.0 * (1.0 + Math.min(dependents, MAX_DEPENDENTS)) / 5.0;
        
        double maxSpendingLimit = 20000.0; 
        spendingLimit = Math.min(spendingLimit, maxSpendingLimit);

        double expensesToApply = Math.min(personalExpenses, spendingLimit);

        double rebatePercentage;
        double annualLimit = annualGrossIncome;
        
        if (annualLimit <= MIN_INCOME_FOR_REBATE) {
            rebatePercentage = 0.20;
        } else {
            rebatePercentage = 0.10;
        }
        
        double maxRebateAmount = (CANASTA_FAMILIAR_BASICA * 7.0) * rebatePercentage;

        double calculatedRebate = expensesToApply * rebatePercentage; 

        return Math.min(calculatedRebate, maxRebateAmount);
    }

    public double calculateMonthlyRetention(double finalAnnualTax) {
        return finalAnnualTax / 12.0;
    }
}