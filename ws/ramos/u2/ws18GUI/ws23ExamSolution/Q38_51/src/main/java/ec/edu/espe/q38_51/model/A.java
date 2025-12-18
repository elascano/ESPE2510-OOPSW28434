package ec.edu.espe.q38_51.model;

import java.util.ArrayList;

/**
 *
 * @author LABS-ESPE
 */
public class A {            //0.5
    
    private ArrayList <A> as;       //0.3

    public A(ArrayList<A> as) {
        this.as = as;
    }

    @Override
    public String toString() {      //0.5
        return "A{" + "as=" + as + '}';
    }

    /**
     * @return the as
     */
    public ArrayList <A> getAs() {
        return as;
    }

    /**
     * @param as the as to set
     */
    public void setAs(ArrayList <A> as) {
        this.as = as;
    }
    
}
