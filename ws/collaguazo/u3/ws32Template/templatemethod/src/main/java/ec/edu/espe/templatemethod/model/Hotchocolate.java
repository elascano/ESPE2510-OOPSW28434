/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.templatemethod.model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Steven Loza @ESPE
 */
public class Hotchocolate extends CaffeineBeverage {
    @Override
    public void brew() {
        System.out.println("boiling water");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding Milk");
    }

    @Override
    public boolean wantsCondiments() {
        String answer = getUserInput();
        if (answer.toLowerCase().startsWith("y")) {
        System.out.println("You now have a hot chocolate with milk Pablo Collaguazo");
        return true;
    } else {
        System.out.println("You now have a hot chocolate without milk Pablo Collaguazo");
        return false;
    }
}
        
    


    private String getUserInput() {
        String answer = null;
        System.out.println("Would you like hot chocolate with milk (y/n)? ");
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

    

