package ec.edu.espe.operations;

/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public class Operation {

    public static final float FBE = 11902.00F;
    private static final float BASE_UVC = 5355.90F;
    
    private static final float[][] ANNUAL_TAX_BRACKETS = {
        {0.00F, 0.00F, 0.00F},       
        {11902.00F, 0.00F, 0.05F},   
        {15159.00F, 163.00F, 0.10F}, 
        {19682.00F, 615.00F, 0.12F}, 
        {26031.00F, 1377.00F, 0.15F},
        {34255.00F, 2611.00F, 0.20F},
        {45407.00F, 4841.00F, 0.25F},
        {60450.00F, 8602.00F, 0.30F},
        {80605.00F, 14648.00F, 0.35F},
        {107199.00F, 23956.00F, 0.37F}
    };

    /**
     * T0D0 Calculate the Annual Excess Fraction (Taxable Base).
     * @param annualSalary Total Annual Income.
     * @param annualSpentDeductible Annual Personal Expenses.
     * @param dependents Number of dependents
     * @return Annual Taxable Income.
     */
    
    public static float calculateAnnualTaxableBase(float annualSalary, float annualSpentDeductible, int dependents) {
        
        float maxDeductibleExpenses = BASE_UVC * (1 + 0.5F * dependents); 
        float actualDeductibleExpenses = Math.min(annualSpentDeductible, maxDeductibleExpenses);
        float incomeAfterExpenses = annualSalary - actualDeductibleExpenses;
        float taxableBase = incomeAfterExpenses - FBE;
        
        return Math.max(0, taxableBase);
    }

    /**
     * T0D0 Calculate your ANNUAL Income Tax.
     * @param taxableBase Base The annual taxable base.
     * @return Amount of annual Income Tax.
     */
    
    public static float calculateAnnualIncomeTax(float taxableBase) {
        float annualTax = 0.0F;

        for (int i = 0; i < ANNUAL_TAX_BRACKETS.length; i++) {
            float basicFraction = ANNUAL_TAX_BRACKETS[i][0];
            float fixedTax = ANNUAL_TAX_BRACKETS[i][1];
            float marginalRate = ANNUAL_TAX_BRACKETS[i][2];
            
            if (taxableBase >= basicFraction) {
                float upTo = (i < ANNUAL_TAX_BRACKETS.length - 1) 
                             ? ANNUAL_TAX_BRACKETS[i + 1][0] 
                             : Float.MAX_VALUE; 

                annualTax = fixedTax;
                float amountTaxedInBracket = taxableBase - basicFraction;
                
                if (taxableBase < upTo) {
                    amountTaxedInBracket = Math.min(amountTaxedInBracket, upTo - basicFraction);
                }
                
                annualTax += amountTaxedInBracket * marginalRate;
                
                if (taxableBase < upTo) {
                    break; 
                }
            }
        }
        
        return annualTax;
    }
   
}
