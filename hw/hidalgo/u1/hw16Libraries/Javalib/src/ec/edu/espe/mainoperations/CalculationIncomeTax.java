package ec.edu.espe.mainoperations;
import java.util.List;
/**
 *
 * @author Mikael Hidalgo, KNOWLEDGE ENCAPSULATE, @ESPE
 */
public class CalculationIncomeTax {
       private static final double CBF_JANUARY_2025 = 798.31;
        
    private static class Tramo{
        final double lowerLimit;
        final double upperLimit;
        final double  basicTax;
        final double excessPercentage;

        public Tramo(double lowerLimit, double upperLimit, double basicTax, double excessPercentage) {
            this.lowerLimit = lowerLimit;
            this.upperLimit = upperLimit;
            this.basicTax = basicTax;
            this.excessPercentage = excessPercentage;
        }
    }
    
    private static final List<Tramo> TABLE_TRAMOS_2025 = List.of(
        new Tramo(0, 12081, 0, 0.00),
        new Tramo(12081, 15311, 0, 0.05),
        new Tramo(15311, 19956, 161, 0.10),
        new Tramo(19956, 26458, 626, 0.12),
        new Tramo(26458, 35339, 1406, 0.15),
        new Tramo(35339, 47196, 2748, 0.20),
        new Tramo(47196, 63558, 5120, 0.25),
        new Tramo(63558, 84720, 9211, 0.30),
        new Tramo(84720, 112963, 15560, 0.35),
        new Tramo(112963, Double.POSITIVE_INFINITY, 25445, 0.37)
    );
    
    private CalculationIncomeTax(){};
    
    
    public static double finalBalance(double grossIncome, double ieesContribution, double personalExpenses, int familyDependents, double withholding){
        double taxableBase = Math.max(0, grossIncome - ieesContribution);
        double taxDue = taxDue(taxableBase);
        double discountExpenses = discount(personalExpenses, familyDependents);
        
        double finalBalance = taxDue - discountExpenses - withholding;
        return finalBalance;
    }
    
    public static double taxDue(double taxableBase){
        for (Tramo tramo : TABLE_TRAMOS_2025) {
            if (taxableBase <= tramo.upperLimit){
                double surplus = taxableBase - tramo.lowerLimit;
                double surplusTax = surplus * tramo.excessPercentage;
                return tramo.basicTax + surplusTax;
            }
        }
        return 0;
    }
    
    public static double discount(double personalExpenses, int familyDependests){
        int numTaxBand = getTaxBand(familyDependests);
        double maximunSpendingLimit = numTaxBand * CBF_JANUARY_2025;
        double baseDiscount = Math.min(personalExpenses, maximunSpendingLimit);
        return baseDiscount*0.18;
    }
    
    private static int getTaxBand(int dependets){
        if (dependets <= 0) return 7;
        if (dependets == 1) return 9;
        if (dependets == 2) return 11;
        if (dependets == 3) return 14;
        if (dependets == 4) return 17;
        return 20;
    }

}
