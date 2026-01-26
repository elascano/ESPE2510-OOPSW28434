package ec.edu.espe.hwcomposite.view;

import ec.edu.espe.hwcomposite.controller.Client;
import ec.edu.espe.hwcomposite.model.Clerk;
import ec.edu.espe.hwcomposite.model.Manager;
import ec.edu.espe.hwcomposite.model.President;
import ec.edu.espe.hwcomposite.model.Teller;

public class Setup {

    public static void main(String args[]) {

        Teller lonny = new Teller("Lonny");   // Leaf
        Clerk cal = new Clerk("Cal");         // Leaf
        Manager able = new Manager("Able");   // Composite
        able.add(lonny);
        able.add(cal);

        Teller juanita = new Teller("Juanita"); // Leaf
        Teller tina = new Teller("Tina");       // Leaf
        Teller thelma = new Teller("Thelma");   // Leaf
        Manager becky = new Manager("Becky");   // Composite
        becky.add(juanita);
        becky.add(tina);
        becky.add(thelma);

        President pete = President.getPresident("Pete"); // Composite
        pete.add(able);
        pete.add(becky);

        Client.employee = pete; // Root
        Client.doClientTasks();
    }
}
