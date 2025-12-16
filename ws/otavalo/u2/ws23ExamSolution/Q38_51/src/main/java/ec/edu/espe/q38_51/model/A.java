package ec.edu.espe.q38_51.model;

import java.util.ArrayList;

/**
 *
 * @author Arelys Otavalo, The POOwer Rangers of Programming,@espe
 */
public class A {   //0.5
    ArrayList<A> as;  //0.3

    public A(ArrayList<A> as) {
        this.as = as;
    }

    public ArrayList<A> getAs() {   //0.2
        return as;
    }

    public void setAs(ArrayList<A> as) {
        this.as = as;
    }

    @Override
    public String toString() {
        return "A{" + "as=" + as + '}';
    }
    
    
}