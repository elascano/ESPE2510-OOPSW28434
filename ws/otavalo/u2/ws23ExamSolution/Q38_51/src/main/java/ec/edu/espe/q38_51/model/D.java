package ec.edu.espe.q38_51.model;
import java.util.ArrayList;
/**
 *
 * @author Arelys Otavalo, The POOwer Rangers of Programming,@espe
 */
public class D extends A {  //0.1
    E es[]= new E[5]; //0.3
    ArrayList<F> fs;  //0.3

    @Override
    public String toString() {
        return "D{" + "es=" + es + ", fs=" + fs + super.toString()+ '}';
    }
    

    public D(E[] es, ArrayList<F> fs, ArrayList<A> as) {  //0.4
        super(as);
        this.es = es;
        this.fs = fs;
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

    public ArrayList<A> getAs() {
        return as;
    }

    public void setAs(ArrayList<A> as) {
        this.as = as;
    }
    
}