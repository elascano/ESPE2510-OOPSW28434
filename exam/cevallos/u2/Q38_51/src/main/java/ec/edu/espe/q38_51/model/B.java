/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.q38_51.model;

/**
 *
 * @author Mateo Cevallos Object Masters
 */
public class B extends A{

    private B b;
    
   
    public B(B b, A a) {
        super(a);
        this.b = b;
    }

    @Override
    public String toString() {
        return "B{" + "b=" + getB() + '}';
    }

    /**
     * @return the b
     */
    public B getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(B b) {
        this.b = b;
    }
    
    
    
    
    
}
