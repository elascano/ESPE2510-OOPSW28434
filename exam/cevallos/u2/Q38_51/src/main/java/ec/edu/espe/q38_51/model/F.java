/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.q38_51.model;

/**
 *
 * @author Mateo Cevallos Object Masters
 */
public class F {
    private D d;

    public F(D d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "F{" + "d=" + getD() + '}';
    }

    /**
     * @return the d
     */
    public D getD() {
        return d;
    }

    /**
     * @param d the d to set
     */
    public void setD(D d) {
        this.d = d;
    }
    
}
