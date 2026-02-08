package ec.edu.espe.hwtemplatemethod.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HutChocolate extends CaffeineBeverage {

    @Override
    public void brew() {
        System.out.println("Preparing hot chocolate by kevin chalan");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding milk to hot chocolate by kevin chalan");
    }

    @Override
    public boolean wantsCondiments() {
        String answer = getUserInput();
        if (answer.toLowerCase().startsWith("y")) {
            return true;
        } else {
            System.out.println("Hot chocolate without milk by kevin chalan");
            return false;
        }
    }

    private String getUserInput() {
        String answer = null;
        System.out.println("Would you like milk with your hot chocolate (y/n)?");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            answer = in.readLine();
        } catch (IOException e) {
            System.err.println("IO error");
        }

        if (answer == null) {
            return "n";
        }
        return answer;
    }
}
