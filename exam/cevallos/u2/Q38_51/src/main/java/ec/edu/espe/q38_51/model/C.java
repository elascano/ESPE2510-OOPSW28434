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
    
    private ArrayList<E> eS = new ArrayList<>();

    public C(A a) {
        super(a);
    }

    @Override
    public String toString() {
        return "C{" + "eS=" + geteS() + '}';
    }

    /**
     * @return the eS
     */
    public ArrayList<E> geteS() {
        return eS;
    }

    /**
     * @param eS the eS to set
     */
    public void seteS(ArrayList<E> eS) {
        this.eS = eS;
    }
    
    
    
    
}
