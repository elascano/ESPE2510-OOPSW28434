package ec.edu.espe.q38_51.model;

import java.util.ArrayList;

/**
 *
 * @author Maryuri Quina, @ESPE
 */
public class B extends A{  //1
    private ArrayList<H> hs;  //0.5 private ArrayList<G> gs

    public B(ArrayList<H> hs, ArrayList<A> as) { //0.5
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
