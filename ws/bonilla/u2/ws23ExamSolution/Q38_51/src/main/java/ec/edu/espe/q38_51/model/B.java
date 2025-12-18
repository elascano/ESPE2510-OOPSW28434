package ec.edu.espe.q38_51.model;

import java.util.ArrayList;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class B extends A{
    private ArrayList<H> hs;

    @Override
    public String toString() {
        return "B{" + "hs=" + hs + super.toString()+'}';
    }
    
    public B(ArrayList<H> hs, ArrayList<A> as) {
        super(as);
        this.hs = hs;
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
