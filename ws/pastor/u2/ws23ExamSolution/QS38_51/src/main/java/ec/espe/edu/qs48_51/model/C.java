package ec.espe.edu.qs48_51.model;

import java.util.ArrayList;

/**
 *
 * @author Mathews Pastor, POOwer Ranger of Programing, @ESPE
 */
public class C extends A{
     ArrayList<E> es;

    @Override
    public String toString() {
        return "C{" + "es=" + es + super.toString() + '}';
    }

    public C(ArrayList<E> es, ArrayList<A> as) {
        super(as);
        this.es = es;
    }
     
     
    
    
}
