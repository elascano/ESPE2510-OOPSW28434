package computings;
/**
 *
 * @author Mateo Cevallos
 */


public class TaxOperation {
    
    // Internal class to represent each tax bracket
    public static class TaxBracket {
        private double basicFraction;
        private double excessUpTo;
        private double basicFractionTax;
        private double excessPercentage;
        
        public TaxBracket(double bf, double eut, double bft, double ep) {
            this.basicFraction = bf;
            this.excessUpTo = eut;
            this.basicFractionTax = bft;
            this.excessPercentage = ep;
        }
        
        // Getters
        public double getBasicFraction() { return basicFraction; }
        public double getExcessUpTo() { return excessUpTo; }
        public double getBasicFractionTax() { return basicFractionTax; }
        public double getExcessPercentage() { return excessPercentage; }
    }
    
    // Tax brackets according to the image (public constant)
    public static final TaxBracket[] TAX_BRACKETS_2024 = {
        new TaxBracket(0, 11902, 0, 0),
        new TaxBracket(11902, 15159, 0, 5),
        new TaxBracket(15159, 19682, 163, 10),
        new TaxBracket(19682, 26031, 615, 12),
        new TaxBracket(26031, 34255, 1377, 15),
        new TaxBracket(34255, 45407, 2611, 20),
        new TaxBracket(45407, 60450, 4841, 25),
        new TaxBracket(60450, 80605, 8602, 30),
        new TaxBracket(80605, 107199, 14648, 35),
        new TaxBracket(107199, Double.MAX_VALUE, 23956, 37)
    };
    
    /**
     * Calculates annual tax based on annual salary
     * @param annualSalary Annual salary in dollars
     * @return Calculated annual tax
     */
    public static double calculateAnnualTax(double annualSalary) {
        if (annualSalary <= 0) {
            return 0;
        }
        
        for (int i = TAX_BRACKETS_2024.length - 1; i >= 0; i--) {
            TaxBracket bracket = TAX_BRACKETS_2024[i];
            
            if (annualSalary > bracket.getBasicFraction()) {
                double excess = annualSalary - bracket.getBasicFraction();
                double excessTax = excess * (bracket.getExcessPercentage() / 100);
                return bracket.getBasicFractionTax() + excessTax;
            }
        }
        return 0;
    }
    
    /**
     * Calculates monthly tax considering deductible expenses
     * @param monthlySalary Monthly salary in dollars
     * @param deductibleExpenses Annual deductible expenses
     * @return Monthly tax to pay
     */
    public static double calculateMonthlyTax(double monthlySalary, double deductibleExpenses) {
        double annualSalary = monthlySalary * 12;
        double taxBase = Math.max(0, annualSalary - deductibleExpenses);
        double annualTax = calculateAnnualTax(taxBase);
        return annualTax / 12;
    }
    
    /**
     * Calculates the annual tax base
     * @param monthlySalary Monthly salary
     * @param deductibleExpenses Annual deductible expenses
     * @return Tax base for tax calculation
     */
    public static double calculateTaxBase(double monthlySalary, double deductibleExpenses) {
        double annualSalary = monthlySalary * 12;
        return Math.max(0, annualSalary - deductibleExpenses);
    }
    
    /**
     * Gets the tax bracket for a given annual salary
     * @param annualSalary Annual salary
     * @return Corresponding tax bracket
     */
    public static TaxBracket getTaxBracket(double annualSalary) {
        for (TaxBracket bracket : TAX_BRACKETS_2024) {
            if (annualSalary <= bracket.getExcessUpTo()) {
                return bracket;
            }
        }
        return TAX_BRACKETS_2024[TAX_BRACKETS_2024.length - 1];
    }
    
}
