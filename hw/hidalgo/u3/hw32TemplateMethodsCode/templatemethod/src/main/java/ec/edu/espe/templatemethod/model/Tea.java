package ec.edu.espe.templatemethod.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Mikael Hidalgo
 */
public class Tea extends CaffeineBeverage {
    @Override
    public void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    public void addCondiments() {
        System.out.println("adding lemon");
    }

    @Override
    public boolean wantsCondiments() {
        String answer = getUserInput();
        if (answer.toLowerCase().startsWith("y")){
            System.out.println("here you are your lemon te prepared by Daniel");
            return true;
        }else{
            System.out.println("This is not a lemon te by Daniel ");
            return false;
        }
    }

    private String getUserInput() {
        String answer = null;
        System.out.println("Would you like lemon with your tea (y/n)? ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            answer = in.readLine();
        } catch (IOException e) {
            System.err.println("IO error trying to read your answer");
        }
        if (answer == null) return "no";
        return answer;
    }    
}
