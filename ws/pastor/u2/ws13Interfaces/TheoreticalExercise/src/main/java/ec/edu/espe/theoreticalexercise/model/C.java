package ec.edu.espe.theoreticalexercise.model;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class C extends B {
    private int c;
    private int d;
    
    public C(int c, int d, int a, int b){
        super (a, b);
        this.c = c;
        this.d = d;
    }

    @Override
    public String toString() {
        return "C{"+ "..." + super.toString() + "c=" + c + ", d=" + d + '}';
    }
    
    
    
    public void m(int q, int p){
        System.out.println("This is the methid m() with parameters --> p" + p + "q  " + q);
        
    }
    
    public void m(int a) {
        System.out.println("method m. of C that implements of A");
    }

    /**
     * @return the c
     */
    public int getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(int c) {
        this.c = c;
    }

    /**
     * @return the d
     */
    public int getD() {
        return d;
    }

    /**
     * @param d the d to set
     */
    public void setD(int d) {
        this.d = d;
    }
}
