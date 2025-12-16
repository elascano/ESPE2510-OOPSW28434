/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.q38_51.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author LABS-ESPE
 */
public class D extends A{
    private E es[];
    private ArrayList <F> fs;

    public D(E[] es, ArrayList<F> fs, ArrayList<A> as) {
        super(as);
        this.es = es;
        this.fs = fs;
    }

    @Override
    public String toString() {
        return "D{" + "es=" + Arrays.toString(es) + ", fs=" + fs + super.toString() + '}';
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
    public ArrayList <F> getFs() {
        return fs;
    }

    /**
     * @param fs the fs to set
     */
    public void setFs(ArrayList <F> fs) {
        this.fs = fs;
    }
    
    
    
}
