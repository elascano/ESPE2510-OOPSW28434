package ec.edu.espe.incomeTaxesCalculator;
import java.util.List;
/**
 *
 * @author JOSUE
 */
public class IncomeTaxesCalculator {

    public static final class Bracket {
        public final double lower, upper, rate, baseTax;
        Bracket(double lower, double upper, double rate, double baseTax) {
            this.lower = lower; this.upper = upper; this.rate = rate; this.baseTax = baseTax;
        }
        boolean contains(double x){ return x >= lower && x < upper; }
    }

    public static final class Result {
        public final double annualGross, taxableIncome, causedTax, gpRebate, annualTax, monthlyWithholding;
        Result(double annualGross, double taxableIncome, double causedTax, double gpRebate) {
            this.annualGross = annualGross;
            this.taxableIncome = taxableIncome;
            this.causedTax = causedTax;
            this.gpRebate = gpRebate;
            this.annualTax = Math.max(0.0, causedTax - gpRebate);
            this.monthlyWithholding = this.annualTax / 12.0;
        }
    }

    private static final double CFB_2025 = 798.31;

    private final List<Bracket> brackets = List.of(
        new Bracket(0,       12081,   0.00,     0),
        new Bracket(12081,   15387,   0.05,     0),
        new Bracket(15387,   19978,   0.10,   165),
        new Bracket(19978,   26422,   0.12,   624),
        new Bracket(26422,   34770,   0.15,  1398),
        new Bracket(34770,   46089,   0.20,  2650),
        new Bracket(46089,   61359,   0.25,  4941),
        new Bracket(61359,   81817,   0.30,  8731),
        new Bracket(81817,  108810,   0.35, 14869),
        new Bracket(108810, Double.POSITIVE_INFINITY, 0.37, 24316)
    );

    private static int canastas(int dependents){
        if (dependents <= 0) return 7;
        if (dependents == 1) return 9;
        if (dependents == 2) return 11;
        if (dependents == 3) return 14;
        if (dependents == 4) return 17;
        return 20;
    }

    public Result compute(double monthlySalary, double annualSpent, int dependents){
        if (monthlySalary < 0 || annualSpent < 0 || dependents < 0)
            throw new IllegalArgumentException("Inputs must be non-negative");

        double annualGross = monthlySalary * 12.0;
        double taxable = annualGross;

        double causedTax = 0.0;
        for (Bracket b : brackets) {
            if (b.contains(taxable)) {
                causedTax = b.baseTax + (taxable - b.lower) * b.rate;
                break;
            }
        }

        double cap = CFB_2025 * canastas(dependents);
        double gpRebate = 0.18 * Math.min(annualSpent, cap);

        return new Result(annualGross, taxable, causedTax, gpRebate);
    }
}  

