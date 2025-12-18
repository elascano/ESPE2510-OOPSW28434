package ec.espe.edu.qs48_51.model;

import java.util.ArrayList;

/**
 *
 * @author Mathews Pastor, POOwer Ranger of Programing, @ESPE
 */
public class D extends A {
    private E es[] = new E[5];
    private ArrayList<F> efs;

    public D(E[] es2, ArrayList<F> efs, ArrayList<A> as) {
        super(as);
        this.efs = efs;
    }

    @Override
    public String toString() {
        return "D{" + "es=" + es + ", efs=" + efs + super.toString() + '}';
    }

    

    /**
     * @param es the es to set
     */
    public void setEs(E[] es) {
        this.setEs(es);
    }

    /**
     * @return the efs
     */
    public ArrayList<F> getEfs() {
        return efs;
    }

    /**
     * @param efs the efs to set
     */
    public void setEfs(ArrayList<F> efs) {
        this.efs = efs;
    }

    /**
     * @return the es
     */
    public E[] getEs() {
        return es;
    }
}
