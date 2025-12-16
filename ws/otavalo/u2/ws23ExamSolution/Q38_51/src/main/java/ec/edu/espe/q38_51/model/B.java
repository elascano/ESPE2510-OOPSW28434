package ec.edu.espe.q38_51.model;

import java.util.ArrayList;

/**
 *
 * @author Arelys Otavalo, The POOwer Rangers of Programming,@espe
 */
public class B extends A { //1 
    ArrayList<H> hs;  //0.5

    @Override
    public String toString() {
        return "B{" + "hs=" + hs + super.toString()+ '}';
    }

    
    public B(ArrayList<H> hs, ArrayList<A> as) {
        super(as);
        this.hs = hs;
    }

    public ArrayList<H> getHs() {
        return hs;
    }

    public void setHs(ArrayList<H> hs) {
        this.hs = hs;
    }

    public ArrayList<A> getAs() {
        return as;
    }

    public void setAs(ArrayList<A> as) {
        this.as = as;
    }
    
    
}