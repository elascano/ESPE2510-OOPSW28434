package ec.edu.espe.q47_60.Controller;

import ec.edu.espe.q47_60.Model.A;
import ec.edu.espe.q47_60.Model.B;
import ec.edu.espe.q47_60.Model.C;
import ec.edu.espe.q47_60.Model.D;
import ec.edu.espe.q47_60.Model.E;
import ec.edu.espe.q47_60.Model.G;
import ec.edu.espe.q47_60.Model.J;

/**
 *
 * @author Steven Loza
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("=== UML Diagram Demo ===");

        J j = new J();
        G g = new G(j);

        B b = new B(g);
        C c = new C();
        D d = new D();

        E e1 = new E();
        E e2 = new E();

        c.addE(e1);
        d.addE(e2);

        b.describe();
        c.describe();
        d.describe();

        A parent = new C();
        parent.addChild(b);
        parent.addChild(c);

        System.out.println("Demo completed.");
    }
}
