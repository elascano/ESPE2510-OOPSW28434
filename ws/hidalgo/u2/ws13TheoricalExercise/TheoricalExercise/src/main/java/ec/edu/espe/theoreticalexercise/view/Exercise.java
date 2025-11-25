package ec.edu.espe.theoreticalexercise.view;
import ec.edu.espe.theoreticalexercise.model.A;
import ec.edu.espe.theoreticalexercise.model.B;
import ec.edu.espe.theoreticalexercise.model.C;
/**
 *
 * @author Mikael Hidalgo
 */
public class Exercise {
    public static void main(String[] args) {
      
        //No se puede instanciar A pq es una interface ni B pq es una clase abtracta
        A a;
        B b;
        C c;
        
        
        
        
        /*a=null;
        b=null;
        System.out.println("a-->"+a.getClass().getName());
        System.out.println("b -->"+ b.getClass().getName());*/
        
        a = new C(0, 0, 0, 0);
        b = new C(1, 1, 1, 1);
        c = new C(2, 2, 2, 2);
        
        System.out.println("a -->"+a);
        System.out.println("b -->"+b);
        System.out.println("c -->"+c);
        


//La instanciacion le da el tipo de clase al objeto
        System.out.println("a class is"+a.getClass().getName());
        System.out.println("b class is"+b.getClass().getName());
        System.out.println("c class is"+c.getClass().getName());
    } 
}

