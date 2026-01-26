package ec.edu.espe.templatemethod.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */

public class Tea extends CaffeineBeverage {

    void brew() {
        System.out.println("Steep the tea");
    }

    void addCondiments() {
        System.out.println("Adding lemon...");
        System.out.println("Here you are your lemon tea made by Arelis Bonilla");
    }

    boolean wantsCondiments() {
        String answer = getUserInput();

        if (answer.toLowerCase().startsWith("y")) {
            return true;
        } else {
            System.out.println("This is a non lemon tea made by Arelis Bonilla");
            return false;
        }
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