package ec.espe.edu.incomeTaskOperation;

/**
 *
 * @author Paulo Ramos
 */

public class IncomeTax {

    private final double[][] brackets = {
        {0,       11902,   0,      0.00},
        {11902,   15159,   0,      0.05},
        {15159,   19682,   163,    0.10},
        {19682,   26031,   617,    0.12},
        {26031,   34255,   1377,   0.15},
        {34255,   45407,   2611,   0.20},
        {45407,   60450,   4841,   0.25},
        {60450,   80665,   8591,   0.30},
        {80665,  107199,  14466,   0.35},
        {107199, Double.MAX_VALUE, 23956, 0.37}
    };

    //Calculate annual tax
    public double calculateAnnualTax(double annualIncome) {
        for (double[] bracket : brackets) {
            double lower = bracket[0];
            double upper = bracket[1];
            double baseTax = bracket[2];
            double rate = bracket[3];

            if (annualIncome <= upper) {
                double excess = annualIncome - lower;
                double totalTax = baseTax + (excess * rate);
                return Math.round(totalTax * 100.0) / 100.0;
            }
        }
        return 0.0;
    }

    //Calculate monthly tax
    public double calculateMonthlyTax(double monthlySalary, double monthlyExpenses) {
        double annualIncome = monthlySalary * 12;
        double annualExpenses = monthlyExpenses * 12;
        double taxableBase = Math.max(0, annualIncome - annualExpenses);

        double annualTax = calculateAnnualTax(taxableBase);
        double monthlyTax = annualTax / 12;

        return Math.round(monthlyTax * 100.0) / 100.0;
    }

    //Find the tax bracket
    public String findTaxBracket(double annualIncome) {
        for (double[] bracket : brackets) {
            double lower = bracket[0];
            double upper = bracket[1];
            double rate = bracket[3];

            if (annualIncome <= upper) {
                return String.format(
                    "Your income is in the range $%.2f - $%.2f with a %.0f%% tax rate.",
                    lower, upper, rate * 100
                );
            }
        }
        return "Income exceeds all defined brackets.";
    }
}
