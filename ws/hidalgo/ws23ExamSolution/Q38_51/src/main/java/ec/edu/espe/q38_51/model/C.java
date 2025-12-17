package ec.edu.espe.q38_51.model;

import java.util.ArrayList;

/**
 *
 * @author Mikael Hidalgo, Paradigm, @ESPE
 */
public class C extends A{
    
    private ArrayList<E> es;

    @Override
    public String toString() {
        return "C{" + "es=" + es + super.toString() + '}';
    }

    public C(ArrayList<E> es, ArrayList<A> as) {
        super(as);
        this.es = es;
    }

    /**
     * @return the es
     */
    public ArrayList<E> getEs() {
        return es;
    }

    /**
     * @param es the es to set
     */
    public void setEs(ArrayList<E> es) {
        this.es = es;
    }
    
    
    
    
}
