
package ec.edu.espe.composite.templatemethod.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Thais Santórum
 */
public class Coffee extends CaffeineBeverage {
    public void brew() { System.out.println("Dripping coffee through filter"); }
    public void addCondiments() { System.out.println( "Adding sugar and milk" ); }
    public boolean wantsCondiments() { 
    String answer = getUserInput();
    if ( answer.toLowerCase().startsWith( "y" )) { return true; }
    else { return false; }
    
    }
    private String getUserInput() {
        String answer = null;
        System.out.println("Would you like milk and sugar with your coffee (y/n)?");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            answer = in.readLine();
        } catch (Exception e) {
            answer = "n"; 
        }
        return answer;
    }

}