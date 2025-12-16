package ec.edu.espe.q38_51.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("UML Diagram");

        J j = new J();
        H gService = new G(j);

        B b = new B("Object B", gService);

        C c = new C("Object C");
        c.addElement(new E("E1"));
        c.addElement(new E("E2"));

        D d = new D("Object D");
        d.addElement(new E("E3"));

        b.display();
        System.out.println();

        c.display();
        System.out.println();

        d.display();

        System.out.println("Exiting program...");
    }
}

