package ec.edu.espe.templatemethod.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class Coffee extends CaffeineBeverage {

    void brew() {
        System.out.println("Dripping coffee through filter");
    }

    void addCondiments() {
        System.out.println("Adding sugar and milk");
    }

    boolean wantsCondiments() {
        String answer = getUserInput();
        return answer.toLowerCase().startsWith("y");
    }

    private String getUserInput() {
        String answer = "no";
        System.out.println("Would you like milk and sugar with your coffee (y/n)? ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            answer = in.readLine();
        } catch (IOException e) {
            answer = "no";
        }
        return answer;
    }
}