/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chikenfarmsimulator.model;

/**
 *
 * @author ADRIAN TOAPANTA, Object Masters, @ESPE
 */
public class Egg {
    private char size;

    @Override
    public String toString() {
        return "Egg{" + "size=" + getSize() + '}';
    }

    public Egg(char size) {
        this.size = size;
    }

    /**
     * @return the size
     */
    public char getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(char size) {
        this.size = size;
    }
    
    
    
    
}
