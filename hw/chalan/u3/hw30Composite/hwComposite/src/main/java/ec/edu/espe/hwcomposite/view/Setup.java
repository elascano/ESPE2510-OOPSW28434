package ec.edu.espe.hwcomposite.view;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
import ec.edu.espe.hwcomposite.controller.Client;
import ec.edu.espe.hwcomposite.model.Clerk;
import ec.edu.espe.hwcomposite.model.Manager;
import ec.edu.espe.hwcomposite.model.President;
import ec.edu.espe.hwcomposite.model.Teller;

public class Setup {

    public static void main(String args[]) {

        Teller lonny = new Teller("Lonny");
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

        Client.employee = pete;
        Client.doClientTasks();
    }
}