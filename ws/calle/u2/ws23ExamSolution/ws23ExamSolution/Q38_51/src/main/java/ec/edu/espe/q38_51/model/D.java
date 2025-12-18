package ec.edu.espe.q38_51.model;

import java.util.ArrayList;


/**
 *
 * @author Emily Calle, @ESPE
 */
public class D extends A{   //1
    
    private E es[] = new E[5];     //0.3
    private ArrayList<F> fs;  //0.3

    public D(E[] es, ArrayList<F> fs, ArrayList<A> as) { //0.4
        super(as);
        this.es = es;
        this.fs = fs;
    }

    @Override
    public String toString() {
        return "D{" + "es=" + es + ", fs=" + fs + super.toString()+'}';
    }

    public E[] getEs() {
        return es;
    }

    public void setEs(E[] es) {
        this.es = es;
    }

    public ArrayList<F> getFs() {
        return fs;
    }

    public void setFs(ArrayList<F> fs) {
        this.fs = fs;
    }
    
    
    
    
   
    
    
    
}