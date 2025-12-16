package ec.edu.espe.q31_51.model;

import java.util.ArrayList;



/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class D extends A{

private E es[]= new E[5];
private ArrayList<F> fs;

    @Override
    public String toString() {
        return "D{" + "es=" + es + ", fs=" + fs + super.toString()+'}';
    }


    public D(E[] es, ArrayList<F> fs, ArrayList<A> as) {
        super(as);
        this.es = es;
        this.fs = fs;
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
