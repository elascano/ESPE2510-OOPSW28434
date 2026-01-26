package ec.edu.espe.templatemethod.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Steven Loza
 */
public class HotChocolate extends CaffeineBeverage{
    
    @Override
    public void brew() {
        System.out.println("Steeping the hot chocolate");
    }

    @Override
    public void addCondiments() {
        System.out.println("adding ");
    }

    @Override
    public boolean wantsCondiments() {
        String answer = getUserInput();
        if (answer.toLowerCase().startsWith("y")){
            System.out.println("here you are your hot chocolate with milk prepared by Daniel");
            return true;
        }else{
            System.out.println("This is not a hot chocolate with milk te by Daniel ");
            return false;
        }
    }

    private String getUserInput() {
        String answer = null;
        System.out.println("Would you like milk with your hot chocolate (y/n)? ");
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
