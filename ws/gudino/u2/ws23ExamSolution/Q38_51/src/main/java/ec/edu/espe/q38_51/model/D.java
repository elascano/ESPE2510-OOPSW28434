
package ec.edu.espe.q38_51.model;

import java.util.ArrayList;

/**
 *
 * @author Bryan Gudino
 */
public class D extends A{  //1
    
   private E es[]=new E[5];  //0.3
   
   private ArrayList<F> fs; //0.3

    public D(E[] es2, ArrayList<F> fs, ArrayList<A> as) {
        super(as);
        this.fs = fs;
    }

    @Override
    public String toString() {
        return "D{" + "es=" + es + ", fs=" + fs + super.toString() +'}';
    }



    /**
     * @return the es
     */
    public E[] getEs() {
        return es;
    }

    /**
     * @param es the es to set
     */
    public void setEs(E[] es) {
        this.es = es;
    }

    /**
     * @return the fs
     */
    public ArrayList<F> getFs() {
        return fs;
    }

    /**
     * @param fs the fs to set
     */
    public void setFs(ArrayList<F> fs) {
        this.fs = fs;
    }
    
}
