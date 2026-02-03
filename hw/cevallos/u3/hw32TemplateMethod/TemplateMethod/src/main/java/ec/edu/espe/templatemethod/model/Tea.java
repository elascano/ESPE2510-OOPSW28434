package ec.edu.espe.templatemethod.model;

/**
 *
 * @author Mateo Cevallos, Object Masters
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Tea extends CaffeInBeverage {

    public void brew() {
        System.out.println("Steep the tea");
    }

    public void addCondiments() {
        System.out.println("Adding lemon");
    }

    public boolean wantsCondiments() {
        String answer = getUserInput();
        if (answer.toLowerCase().startsWith("y")) {
            return true;
        } else {
            return false;
        }
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
        return answer;
    }
}
