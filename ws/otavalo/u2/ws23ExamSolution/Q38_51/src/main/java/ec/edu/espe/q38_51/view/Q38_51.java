package ec.edu.espe.q38_51.view;

import ec.edu.espe.q38_51.model.*;
import java.util.ArrayList;

/**
 *
 * @author Arelys Otavalo, The POOwer Rangers of Programming,@espe
 */
public class Q38_51 {

    public static void main(String[] args) {
        System.out.println("Exam --> Arelys Otavalo");
        A a;
        ArrayList as;
        ArrayList bs;
        C cs[] = new C[3];
        D ds[] = new D[4];
        D d;
        H h = new G();
        J j;
        E e;
        F f;
        ArrayList es;
        E es2[] = new E[5];
        ArrayList fs;

        a = null;
        as = new ArrayList<>();
        as.add(a);
        a = new A(as);

        System.out.println("a --> " + a);

        f = new F();
        fs = new ArrayList<>();
        j = new J();

        e = new E();

        for (int i = 0; i <= 5; i++) {
            es2[i] = e;
        }

        fs.add(f);
        fs.add(f);
        fs.add(f);

        d = new D(es2, fs, as);

        System.out.println("d --> " + d);

        h.m(j);

    }

}
