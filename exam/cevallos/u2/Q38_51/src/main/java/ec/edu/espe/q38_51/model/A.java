package ec.edu.espe.q38_51.model;

import java.util.ArrayList;

/**
 *
 * @author Mateo Cevallos Object Masters
 */
public abstract class A {

    private A a;
    private ArrayList<A> aS = new ArrayList<>();

    public A(A a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "A{" + "a=" + getA() + ", aS=" + getaS() + '}';
    }

    /**
     * @return the a
     */
    public A getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(A a) {
        this.a = a;
    }

    /**
     * @return the aS
     */
    public ArrayList<A> getaS() {
        return aS;
    }

    /**
     * @param aS the aS to set
     */
    public void setaS(ArrayList<A> aS) {
        this.aS = aS;
    }
    
    
    
    
}
