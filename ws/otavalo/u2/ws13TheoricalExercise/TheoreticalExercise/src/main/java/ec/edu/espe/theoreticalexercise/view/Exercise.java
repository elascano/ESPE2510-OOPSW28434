
package ec.edu.espe.theoreticalexercise.view;

import ec.edu.espe.theoreticalexercise.model.A;
import ec.edu.espe.theoreticalexercise.model.B;
import ec.edu.espe.theoreticalexercise.model.C;


/**
 *
 * @author Arelys Otavalo
 */
public class Exercise {
    public static void main(String[] args) {
        A a;
        B b;
        C c;

        // a = new A(); no se peude pq A es una interface
        //b = new B(); no se puede pq B es abstracta
    
        a = new C(0, 0, 0, 0);
        b = new C(1, 1, 1, 1);
        c = new C(2, 2, 2, 2);
        
        System.out.println("a -->" + a);
        System.out.println("b -->" + b);
        System.out.println("c -->" + c);
        
        System.out.println("a class is --> " + a.getClass().getSimpleName());
        System.out.println("b class is --> " + b.getClass().getSimpleName());
        System.out.println("c class is --> " + c.getClass().getSimpleName());
    }
    
}
