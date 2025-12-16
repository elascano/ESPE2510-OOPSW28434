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
public class D extends A{

    D d;

    public D(D d, A a) {
        super(a);
        this.d = d;
    }
    
    

    @Override
    public String toString() {
        return "D{" + "eS=" + geteS() + '}';
    }
    
   private ArrayList<E> eS = new ArrayList<>(); 

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
