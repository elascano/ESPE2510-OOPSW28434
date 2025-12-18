package ec.edu.espe.q38_51.model;

import java.util.ArrayList;


/**
 *
 * @author Emily Calle, @ESPE
 */
public class B extends A{   //1

   private ArrayList<H> hs; //0.5 private ArrayList<G> gs

    @Override
    public String toString() {
        return "B{" + "hs=" + hs + super.toString()+ '}';
    }

    public B(ArrayList<H> hs, ArrayList<A> as) { //0.5
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