package ec.edu.espe.q38_51.view;

import ec.edu.espe.q38_51.model.A;
import ec.edu.espe.q38_51.model.B;
import ec.edu.espe.q38_51.model.C;
import ec.edu.espe.q38_51.model.D;
import ec.edu.espe.q38_51.model.E;
import ec.edu.espe.q38_51.model.F;
import ec.edu.espe.q38_51.model.G;
import ec.edu.espe.q38_51.model.H;
import ec.edu.espe.q38_51.model.J;
import java.util.ArrayList;

/**
 *
 * @author Mathews Pastor, Poower Rangers of Programing, @ESPE
 */
public class Q38_51 {
    public static void main(String[] args) {//1
        
        
        //1 (at least 4 objects/ArrayList/Array)
        System.out.println("Exam -->    Daniel Chicaiza!");
        A a;
        ArrayList<A> as;
        ArrayList<B> bs;
        C c[]= new C[3];
        D ds[]=new D[4];
        D d;
        H h = new G();
        J j;
        E e;
        F f;
        ArrayList<E> es;
        E es2[]= new E[5];
        ArrayList <F> fs;
        
        a= null;
        as= new ArrayList<>();
        as.add(a);
        a= new A(as);
        
        
        //1
        System.out.println("a-->"+a);
        
        
        f = new F();
        fs = new ArrayList<>();
        j= new J();
        d= new D(es2,fs,as);
        
        
        e= new E();
        
        for (int i= 0; i<5; i++){
            es2[i] = e;
        }
        
        
        
        fs.add(f);
        fs.add(f);
        fs.add(f);
        
        d=new D(es2, fs,as);
        
        
        System.out.println("d --> " + d);
        
        System.out.println("Calling method m of g -->"+(h.m()));
        
        h.m(j);
        
    }
    
}
