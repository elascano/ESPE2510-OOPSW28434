package model;
/**
 *
 * @author Mateo Aymacaña
 */
public class Egg {
    private char size;

    @Override
    public String toString(){
        return "Egg{" + "size=" + size + '}';
    }
    
    public Egg(char size){
        this.size = size;
    }
    
    public char getSize(){
        return size;
    }
    
    public void setSize(char size){
        this.size = size;
    }
}

