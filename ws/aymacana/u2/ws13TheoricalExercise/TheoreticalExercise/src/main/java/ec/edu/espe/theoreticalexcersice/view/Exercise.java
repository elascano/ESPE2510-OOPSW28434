package ec.edu.espe.theoreticalexcersice.view;

/**
 *
 * @author Mateo Aymacaña, T.A.P. (The Art of Programming), @ESPE
 */
import ec.edu.espe.theoreticalexcersice.model.*;

public class Exercise {

    public static void main(String[] args) {
        A a;
        B b;
        C c;

        a = new C(0, 0, 0, 0);
        b = new C(1, 1, 1, 1);
        c = new C(2, 2, 2, 2);
        
        System.out.println("a ---> " + a);
        System.out.println("b ---> " + b);
        System.out.println("c ---> " + c);
        
        System.out.println("a clss is --> " + a.getClass().getName());
        System.out.println("a clss is --> " + b.getClass().getName());
        System.out.println("a clss is --> " + c.getClass().getName());
    }

}
