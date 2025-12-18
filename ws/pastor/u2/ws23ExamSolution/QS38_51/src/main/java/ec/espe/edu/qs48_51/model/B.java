package ec.espe.edu.qs48_51.model;

import ec.espe.edu.qs48_51.controller.H;
import java.util.ArrayList;

/**
 *
 * @author Mathews Pastor, POOwer Ranger of Programing, @ESPE
 */
public class B extends A {
    private ArrayList<H> hs;

    public B(ArrayList<H> hs, ArrayList<A> as) {
        super(as);
        this.hs = hs;
    }

    @Override
    public String toString() {
        return "B{" + "hs=" + hs + super.toString() + '}';
    }
    
    /**
     * @return the hs
     */
    public ArrayList<H> getHs() {
        return hs;
    }

    /**
     * @param hs the hs to set
     */
    public void setHs(ArrayList<H> hs) {
        this.hs = hs;
    }
    
}
