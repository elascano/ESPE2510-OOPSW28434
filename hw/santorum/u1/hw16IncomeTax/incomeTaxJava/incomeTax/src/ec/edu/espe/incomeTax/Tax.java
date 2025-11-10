
package ec.edu.espe.incomeTax;

/**
 *
 * @author Thais Santórum / Team 6 - Paradigm
 */

public class Tax {

    private double monthlySalary;
    private double deductibleExpenses;

    // Constructor
    public Tax(double monthlySalary, double deductibleExpenses) {
        this.monthlySalary = monthlySalary;
        this.deductibleExpenses = deductibleExpenses;
    }

    // calculate the tax
    public TaxResult calculateTax() {
        // 1️⃣ Calcular ingreso anual y base imponible
        double annualIncome = monthlySalary * 12;
        double taxableBase = Math.max(0, annualIncome - deductibleExpenses);

        // SRI TABLE 
        // FROM / TO / BASE TAX / PERCENTAGE
        double[][] taxTable = {
            {0, 12081, 0, 0},
            {12081, 15387, 0, 0.05},
            {15387, 19978, 165, 0.10},
            {19978, 26422, 624, 0.12},
            {26422, 34770, 1398, 0.15},
            {34770, 46089, 2650, 0.20},
            {46089, 61359, 4914, 0.25},
            {61359, 81817, 8731, 0.30},
            {81817, 108810, 14869, 0.35},
            {108810, Double.MAX_VALUE, 24316, 0.37}
        };

        double baseTax = 0;
        double rate = 0;
        double fractionMin = 0;

        // search range
        for (double[] row : taxTable) {
            if (taxableBase >= row[0] && taxableBase < row[1]) {
                fractionMin = row[0];
                baseTax = row[2];
                rate = row[3];
                break;
            }
        }

        // yearly tax
        double annualTax = baseTax + (taxableBase - fractionMin) * rate;
        if (annualTax < 0) annualTax = 0; 


        double monthlyTax = annualTax / 12;

     
        return new TaxResult(annualIncome, deductibleExpenses, taxableBase, annualTax, monthlyTax);
    }

    // return answers
    public static class TaxResult {
        public double annualIncome;
        public double deductibleExpenses;
        public double taxableBase;
        public double annualTax;
        public double monthlyTax;

        public TaxResult(double annualIncome, double deductibleExpenses, double taxableBase,
                         double annualTax, double monthlyTax) {
            this.annualIncome = annualIncome;
            this.deductibleExpenses = deductibleExpenses;
            this.taxableBase = taxableBase;
            this.annualTax = annualTax;
            this.monthlyTax = monthlyTax;
        }
    }
}