package ec.edu.espe.q38_51.model;
import java.util.ArrayList;
/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class D extends A {   //1
    private E es[]= new E[5];    //0.3
    private ArrayList<F> fs;  //0.3

    @Override
    public String toString() {
        return "D{" + "es=" + es + ", fs=" + fs + super.toString()+'}';
    }

    
    
    
    public D(E[] es, ArrayList<F> fs, ArrayList<A> as) {   //0.4
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
