package ec.edu.espe.q38_51.model;

import java.util.ArrayList;

/**
 *
 * @author Arelys Otavalo, The POOwer Rangers of Programming,@espe
 */
public class C extends A{  //1
    ArrayList<E> es;  //0.3

    @Override
    public String toString() {
        return "C{" + "es=" + es + super.toString()+  '}';
    }

    public C(ArrayList<E> es, ArrayList<A> as) {  //0.5
        super(as);
        this.es = es;
    }
    
}