package ec.edu.espe.templatemethod.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tea extends CaffeineBeverage {

    void brew() {
        System.out.println("Steep the tea");
    }

    void addCondiments() {
        System.out.println("Adding lemon");
    }

    boolean wantsCondiments() {
        String answer = getUserInput();
        return answer.toLowerCase().startsWith("y");
    }

    private String getUserInput() {
        String answer = "no";
        System.out.println("Would you like lemon with your tea (y/n)? ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            answer = in.readLine();
        } catch (IOException e) {
            answer = "no";
        }
        return answer;
    }
}