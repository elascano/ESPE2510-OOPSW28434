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
public class B extends A{
    private ArrayList<H> hs;

    public B(ArrayList<H> hs, ArrayList<A> as) {
        super(as);
        this.hs = hs;
    }

    @Override
    public String toString() {
        return "B{" + "hs=" + hs + super.toString() +'}';
    }

    

    /**
     * @return the hs
     */
    public ArrayList<H> getHs() {
        return hs;
    }

    /**
     * @param hs the hs to set
     */
    public void setHs(ArrayList<H> hs) {
        this.hs = hs;
    }
    
    
}
