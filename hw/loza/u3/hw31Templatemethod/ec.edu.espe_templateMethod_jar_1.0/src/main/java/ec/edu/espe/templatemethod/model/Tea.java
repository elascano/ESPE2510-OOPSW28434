package ec.edu.espe.templatemethod.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Steven Loza, @ESPE
 */
public class Tea extends CaffeineBeverage{
    public void brew(){System.out.println("Steep the tea");}
    public void addCondiments(){System.out.println("Addig lemon");}
    
    public boolean wantsCondiments(){
        String answer = getUserInput();
        return answer.toLowerCase().startsWith("y");
    }
    private String getUserInput(){
        String answer = "n";
        System.out.println("Would you like lemon with your tea (y/n)?");
        BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
        try {
            answer = in.readLine();
        } catch (IOException ioe){
            System.out.println("Error reading the answer");
        }
        
        return answer;
    }
    
}
