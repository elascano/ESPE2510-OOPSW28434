package ec.edu.espe.templatemethod.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Paulo Ramos
 */
public class Coffe extends CaffeineBeverage {

    public void brew() {
        System.out.println("Dripping coffe through filter");
    }

    public void addCondiments() {
        System.out.println("Adding sugar and milk");
    }

    public boolean wantsCondiments() {
        String answer = getUserInput();
        if (answer.toLowerCase().startsWith("y")) {
            return true;
        } else {
            return false;
        }
    }

    public String getUserInput() {
        String answer = null;
        System.out.println("Would you like milk and sugar with your coffe (y/n)?");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            answer = in.readLine();
        } catch (IOException e) {
            System.out.println("Error reading your answer");
        }
        return answer;
    }

}
