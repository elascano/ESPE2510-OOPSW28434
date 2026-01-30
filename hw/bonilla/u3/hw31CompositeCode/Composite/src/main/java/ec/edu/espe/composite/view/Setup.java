package ec.edu.espe.composite.view;

import ec.edu.espe.composite.model.*;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */

public class Setup {

    public static void main(String[] args) {

        Clerk lonny = new Clerk("Lonny");
        Clerk cal = new Clerk("Cal");

        Manager able = new Manager("Able");
        able.add(lonny);
        able.add(cal);

        Teller juanita = new Teller("Juanita");
        Teller tina = new Teller("Tina");
        Teller thelma = new Teller("Thelma");

        Manager becky = new Manager("Becky");
        becky.add(juanita);
        becky.add(tina);
        becky.add(thelma);

        President pete = President.getPresident("Pete");
        pete.add(able);
        pete.add(becky);

        pete.stateName();
    }
}