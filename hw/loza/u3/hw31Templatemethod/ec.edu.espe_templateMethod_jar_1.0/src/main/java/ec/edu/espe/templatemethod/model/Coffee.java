package ec.edu.espe.templatemethod.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Steven Loza, @ESPE
 */
public class Coffee extends CaffeineBeverage {
    public void brew() {System.out.println("Dripping coffee through filter");}
    public void addCondiments() {System.out.println("Adding sugar and milk");}
    
    public boolean wantsCondiments(){
        String answer = getUserInput();
        return answer.toLowerCase().startsWith("y");
    }
    private String getUserInput(){
        String answer = "n";
        System.out.println("Would you like milk and sugar with your coffee (y/n)?");
        BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
        try {
            answer = in.readLine();
        } catch (IOException ioe){
            System.out.println("Error reading the answer");
        }
        return answer;
    }
    
}
