

package ec.edu.espe.q31_51.model;

import java.util.ArrayList;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class A {

    public A(ArrayList<A> as) {//0.5
        this.as = as;
    }

    @Override
    public String toString() {
        return "A{" + "as=" + as + '}';
    }

    /**
     * @return the as
     */
    public ArrayList<A> getAs() {
        return as;
    }

    /**
     * @param as the as to set
     */
    public void setAs(ArrayList<A> as) {
        this.as = as;
    }
private ArrayList<A> as;

}
