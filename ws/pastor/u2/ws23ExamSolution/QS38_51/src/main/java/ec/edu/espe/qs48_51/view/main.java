package ec.edu.espe.qs48_51.view;

import ec.espe.edu.qs48_51.controller.H;
import ec.espe.edu.qs48_51.model.*;
import java.util.ArrayList;

/**
 *
 * @author Mathews Pastor, POOwer Ranger of Programing, @ESPE
 */
public class main {

    public static void main(String[] args) {
        System.out.println("Exam --> Mathews Pastor ");
        A a;
        ArrayList<A> as;
        ArrayList<B> bs;
        C c[] = new C[3];
        D ds[] = new D[4];
        D d;
        H h = new G();
        J j = new J();
        E e;
        F f;
        ArrayList<E> es;
        E es2[] = new E[5];
        ArrayList<F> fs;

        a = null;
        as = new ArrayList<>();
        a = new A(as);
        
        f = new F();
        System.out.println("a --> " + a);
        fs = new ArrayList<>();
        
        e = new E();
        
        for (int i = 0; i < 5; i++){
            es2[i] = e;
        }
        
        fs.add(f);
        fs.add(f);
        fs.add(f);
        
        d = new D(es2,fs,as);
        
        System.out.println("d --> " + d);
        h.m(j);
    }

}
