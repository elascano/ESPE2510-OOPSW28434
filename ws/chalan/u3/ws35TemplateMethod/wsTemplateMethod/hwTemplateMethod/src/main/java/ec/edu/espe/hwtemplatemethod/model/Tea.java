package ec.edu.espe.hwtemplatemethod.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tea extends CaffeineBeverage {

    @Override
    public void brew() {
        System.out.println("this is a tea by kevin chalan");
    }

    @Override
    public void addCondiments() {
        System.out.println("here you are your lemon tea by kevin chalan");
    }

    @Override
    public boolean wantsCondiments() {
        String answer = getUserInput();
        if (answer.toLowerCase().startsWith("y")) {
            return true;
        } else {
            System.out.println("this is a non lemon tea by kevin chalan");
            return false;
        }
    }

    private String getUserInput() {
        String answer = null;
        System.out.println("Would you like lemon with your tea (y/n)?");

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
