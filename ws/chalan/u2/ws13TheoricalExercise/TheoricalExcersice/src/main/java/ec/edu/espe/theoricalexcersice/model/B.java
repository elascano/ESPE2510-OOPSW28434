package ec.edu.espe.theoricalexcersice.model;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
public abstract class B implements A {
    private int a;
    private int b;

    @Override
    public String toString() {
        return "B{" + "a=" + a + ", b=" + b + '}';
    }

    public B(int a, int b) {
        this.a = a;
        this.b = b;
    }
    
    
  
 
    /**
     * @return the a
     */
    public int getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(int a) {
        this.a = a;
    }

    /**
     * @return the b
     */
    public int getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(int b) {
        this.b = b;
    }
    
}
