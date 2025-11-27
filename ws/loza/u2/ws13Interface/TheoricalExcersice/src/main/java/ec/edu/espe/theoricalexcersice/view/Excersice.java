package ec.edu.espe.theoricalexcersice.view;

import ec.edu.espe.theoricalexcersice.model.A;
import ec.edu.espe.theoricalexcersice.model.B;
import ec.edu.espe.theoricalexcersice.model.C;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
public class Excersice {
    public static void main(String[] args) {
    A a;
    B b;
    C c;
    a= new C(0, 0, 0, 0);  
    b= new C(1, 1, 1, 1);  
    c= new C(2, 2, 2, 2); 
    
        System.out.println("a----"+a);
        System.out.println("b----"+b);
        System.out.println("c----"+c);
        
        System.out.println("a class----"+ a.getClass().getName());
        System.out.println("b class----"+ b.getClass().getName());
        System.out.println("c  class----"+ c.getClass().getName());
        
           System.out.println("a class----"+ a.getClass().getSimpleName());
        System.out.println("b class----"+ b.getClass().getSimpleName());
        System.out.println("c  class----"+ c.getClass().getSimpleName());
    }
    
}
