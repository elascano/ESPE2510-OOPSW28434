package ec.edu.espe.q38_51.model;

import java.util.ArrayList;

/**
 *
 * @author Thais Santorum
 */
public class B extends A {
    private ArrayList<G> gs;

    @Override
    public String toString() {
        return  "B{" + "gs=" + gs + super.toString() + '}';
    }

    public B(ArrayList<G> gs, ArrayList<A> as) {
        super(as);
        this.gs = gs;
    }

    /**
     * @return the gs
     */
    public ArrayList<G> getGs() {
        return gs;
    }

    /**
     * @param gs the gs to set
     */
    public void setGs(ArrayList<G> gs) {
        this.gs = gs;
    }
    
    
}
