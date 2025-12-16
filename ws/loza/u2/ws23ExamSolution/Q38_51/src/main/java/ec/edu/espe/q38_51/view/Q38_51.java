package ec.edu.espe.q38_51.view;

import ec.edu.espe.q38_51.model.*;
import java.util.ArrayList;

/**
 *
 * @author Steven Loza
 */
public class Q38_51 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    //1
        //1 (at least 4 objects /arraylist)
        System.out.println("Exam--> Steven Loza");
        
        A a;
        ArrayList <A> as;
        ArrayList<B> bs;
        C c[]= new C[3];
        D ds[]= new D[3];
        D d;
        H h = new G();
        J j;
        E e;
        F f;
        ArrayList<E> es;
        E es2[] = new E[5];
        ArrayList<F> fs;
        
        
        a = null;
        as = new ArrayList<>();
        as.add(a);
        a = new A(as);
        //1
        System.out.println("a--->"+a);
        
        f = new F();
        fs = new ArrayList<>();
        j = new J();
        fs.add(f);
        fs.add(f);
        fs.add(f);
        
        e = new E();
        
        for (int i = 0; i < 5; i++){
            es2[i]=e;
            
        }
        
       d = new D(es2, fs, as); 
        System.out.println("d --> "+d);
        
        System.out.println("calling method m of g -->"+h.m());
        h.m(j);
    }
    
}
