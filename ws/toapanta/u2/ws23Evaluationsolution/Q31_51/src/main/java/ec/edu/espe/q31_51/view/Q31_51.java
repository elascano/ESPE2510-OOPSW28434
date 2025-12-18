
package ec.edu.espe.q31_51.view;

import ec.edu.espe.q31_51.model.A;
import ec.edu.espe.q31_51.model.B;
import ec.edu.espe.q31_51.model.C;
import ec.edu.espe.q31_51.model.D;
import ec.edu.espe.q31_51.model.E;
import ec.edu.espe.q31_51.model.F;
import ec.edu.espe.q31_51.model.G;
import ec.edu.espe.q31_51.model.H;
import ec.edu.espe.q31_51.model.J;
import java.util.ArrayList;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class Q31_51 {
public static void main(String[] args){
    System.out.println("Exam -->Adrian Toapanta");
    A a;
    ArrayList<A> as;
    ArrayList<B> bs;
    C cs[] = new C[3];
    D ds[] = new D[4];
    D d;
    H h = new G();
    J j;
    E e;
    F f;
    ArrayList<E> es;
    E es2[] = new E[5];
    ArrayList <F> fs;
    a = null;
    as = new ArrayList<>();
    as.add(a);
    a = new A(as);
    System.out.println("a -->"+ a);
    f = new F();
    fs = new ArrayList<F>();
    
    
    e = new E ();
    for (int i= 0 ; i <5; i++){
        es2[i]=e;
    }
    fs.add(f);
    fs.add(f);
    fs.add(f);
     d = new D(es2,fs,as);
     System.out.println("d -->"+d);
     
     System.out.println("calling method m of g -->0" + (h.m()));
     h.m(j);
    
}
}
