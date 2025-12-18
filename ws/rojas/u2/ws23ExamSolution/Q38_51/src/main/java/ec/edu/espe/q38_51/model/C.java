package ec.edu.espe.q38_51.model;

import java.util.ArrayList;

/**
 *
 * @author LABS-ESPE
 */
public class C extends A{  //1
    private ArrayList<E>es;     //0.3

    public C(ArrayList<E> es, ArrayList<A> as) {   //0.5
        super(as);
        this.es = es;
    }

    @Override
    public String toString() {
        return "C{" + "es=" + es + super.toString()+'}';
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
