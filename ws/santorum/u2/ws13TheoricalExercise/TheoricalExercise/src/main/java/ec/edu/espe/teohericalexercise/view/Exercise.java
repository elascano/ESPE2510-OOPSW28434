package ec.edu.espe.teohericalexercise.view;

import ec.edu.espe.teohericalexercise.model.A;
import ec.edu.espe.teohericalexercise.model.B;
import ec.edu.espe.teohericalexercise.model.C;

/**
 *
 * @author JOSUE
 */
public class Exercise {
    public static void main(String[] args) {
        System.out.println("---Yes It Run---");
        A a;
        B b;
        C c;
        
      
        a= new C(0, 0, 0, 0);
        b= new C(1, 1, 1, 1);
        c= new C(2, 2, 2, 2);
        System.out.println("a --->  "+a);
        System.out.println("b --->  "+b);
        System.out.println("c --->  "+c);
        
        System.out.println("a class is --> " +a.getClass().getName());
        System.out.println("b class is --> " +b.getClass().getName());
        System.out.println("c class is --> " +c.getClass().getName());
        
         System.out.println("a class is --> " +a.getClass().getSimpleName());
        System.out.println("b class is --> " +b.getClass().getSimpleName());
        System.out.println("c class is --> " +c.getClass().getSimpleName());
        
    }
   

    
}
