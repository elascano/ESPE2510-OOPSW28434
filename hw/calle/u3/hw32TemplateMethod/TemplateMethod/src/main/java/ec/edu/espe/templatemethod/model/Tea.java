package ec.edu.espe.templatemethod.model;

/**
 *
 * @author Emily Calle, @ESPE
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Tea extends CaffeineBeverage {
    @Override
    public void brew() { 
        System.out.println("Steep the tea"); 
    }

    @Override
    public void addCondiments() { 
        System.out.println("Adding lemon"); 
    }

    @Override
    public boolean wantsCondiments() {
        String answer = getUserInput();
        return answer.toLowerCase().startsWith("y");
    }

    private String getUserInput() {
        String answer = null;
        System.out.println("Would you like lemon with your tea (y/n)?");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            answer = in.readLine();
        } catch (Exception e) {
            System.err.println("IO error");
        }
        return (answer == null) ? "no" : answer;
    }
}