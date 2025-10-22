/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec_edu_espe_chickenfarmsimulator.model;

/**
 *
 * @author Maryuri Quiña, Object Masters, @ESPE
 */
public class Egg {
    private char size;
    
    @Override
    public String toString() {
        return "Egg{" + "size=" + size + '}';
    }

    public Egg(char size) {
        this.size = size;
    }

    public char getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }    
}
