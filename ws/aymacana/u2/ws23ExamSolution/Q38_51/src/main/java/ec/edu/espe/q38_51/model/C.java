/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.q38_51.model;

import java.util.ArrayList;

/**
 *
 * @author Mateo Cevallos Object Masters
 */
public class C extends A{
    private ArrayList<E> es[];

    public C(ArrayList<E>[] es, ArrayList<A> as) {
        super(as);
        this.es = es;
    }

    @Override
    public String toString() {
        return "C{" + "es=" + getEs() + super.toString() + '}';
    }

    /**
     * @return the es
     */
    public ArrayList<E>[] getEs() {
        return es;
    }

    /**
     * @param es the es to set
     */
    public void setEs(ArrayList<E>[] es) {
        this.es = es;
    }
    
    



}
