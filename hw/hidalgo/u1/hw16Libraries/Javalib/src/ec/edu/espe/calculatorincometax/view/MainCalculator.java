package ec.edu.espe.calculatorincometax.view;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Mikael Hidalgo, KNOWLEDGE ENCAPSULATE, @ESPE
 */
public class MainCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                try (Scanner scanner = new Scanner(System.in).useLocale(Locale.US)){
            System.out.println("ECUADOR CALCULATOR in the 2025 ");
            double monthlySalary = reedDouble(scanner, "Enter your average monthly salary: ");
            double annualPersonalExpenses = reedDouble(scanner, "Enter your total Annual Personal Expenses: ");
            int depents = reedInt(scanner, "Enter Number of Dependents: ");
            double annualwithholing = reedDouble(scanner, "Enter Annual Withholdings already paid: ");
            
            double annualGrossIncome = monthlySalary * 12;
            double annualIEES = annualGrossIncome * 0.0945;
            
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("es", "EC"));
            double balance = CalculationIncomeTax.finalBalance(annualGrossIncome, annualIEES, annualPersonalExpenses, depents, annualwithholing);
            if (balance > 0){
                System.out.println("RESULT: Tax Payable: " + formatter.format(balance));
            }else{
                System.out.println("Balance in favor: " + formatter.format(Math.abs(balance)));
            }
        } catch(Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }   
    }

    
    public static double reedDouble(Scanner scanner, String message){
        while(true){
            try{
                System.out.print(message);
                return scanner.nextDouble();
            }catch (InputMismatchException e){
                System.err.println("Incorrect data, please enter a number (e.g 1254.66)");
                scanner.next();
            }
        }
    }
    
    public static int reedInt(Scanner scanner, String message){
        while(true){
            try{
                System.out.print(message);
                return scanner.nextInt();
            }catch (InputMismatchException e){
                System.err.println("Incorrect data, please enter a number (e.g 1)");
                scanner.next();
            }
        }
    }

    private static class CalculationIncomeTax {

        private static double finalBalance(double annualGrossIncome, double annualIEES, double annualPersonalExpenses, int depents, double annualwithholing) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }





        public CalculationIncomeTax() {
        }
    }
}
    
