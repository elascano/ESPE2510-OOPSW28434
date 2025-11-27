package ec.edu.espe.teohericalexercise.model;

/**
 *
 * @author JOSUE
 */
public class C extends B{
    private int c;
    private int d;
    public void m(int p, int q){
        System.out.println("This is the method m with parameters --> p"+ p +"q  "+ q);
    }

    public C(int c, int d, int a, int b) {
        super(a, b);
        this.c = c;
        this.d = d;
    }

    @Override
    public String toString() {
        return "C{" + "c=" +super.toString()+ c + ", d=" + d + '}';
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

    @Override
    public void m(int a) {
        System.out.println("method m of C that implements of A");
    }
    
}
