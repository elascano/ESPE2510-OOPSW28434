/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.q38_51.model;



/**
 *
 * @author Mateo Cevallos Object Masters
 */
public class E {
    private E e;

    public E(E e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return "E{" + "e=" + getE() + '}';
    }

    /**
     * @return the e
     */
    public E getE() {
        return e;
    }

    /**
     * @param e the e to set
     */
    public void setE(E e) {
        this.e = e;
    }
    
    
}
