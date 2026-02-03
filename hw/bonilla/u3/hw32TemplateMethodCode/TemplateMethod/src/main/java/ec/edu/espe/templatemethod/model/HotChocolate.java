package ec.edu.espe.templatemethod.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class HotChocolate extends CaffeineBeverage {

    void brew() {
        System.out.println("Mixing chocolate powder with hot water");
    }

    void addCondiments() {
        System.out.println("Adding milk...");
        System.out.println("Here you are your hot chocolate made by Arelis Bonilla");
    }
    
    boolean wantsCondiments() {
        String answer = getUserInput();

        if (answer.toLowerCase().startsWith("y")) {
            return true;
        } else {
            System.out.println("This is a hot chocolate without milk made by Arelis Bonilla");
            return false;
        }
    }

    private String getUserInput() {
        String answer = "no";
        System.out.println("Would you like milk with your hot chocolate (y/n)? ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            answer = in.readLine();
        } catch (IOException e) {
            answer = "no";
        }
        return answer;
    }
}