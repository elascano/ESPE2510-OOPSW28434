package ec.edu.espe.composite.view;

import ec.edu.espe.composite.model.Clerk;
import ec.edu.espe.composite.model.Manager;
import ec.edu.espe.composite.model.President;
import ec.edu.espe.composite.model.Teller;

/**
 *
 * @author Pablo
 */
public class Setup {
    public static void main(String[] args) {
        // Organización de Manager Able
        Manager able = new Manager("Able");
        able.add(new Teller("Lonny"));
        able.add(new Clerk("Cal"));

        // Organización de Manager Becky
        Manager becky = new Manager("Becky");
        becky.add(new Teller("Juanita"));
        becky.add(new Teller("Tina"));
        becky.add(new Teller("Thelma"));

        // Presidente y reportes directos
        President pete = President.getPresident("Pete");
        pete.add(able);
        pete.add(becky);

        // Iniciar cliente
        Client.employee = pete;
        Client.doClientTasks();
    }
    
}
