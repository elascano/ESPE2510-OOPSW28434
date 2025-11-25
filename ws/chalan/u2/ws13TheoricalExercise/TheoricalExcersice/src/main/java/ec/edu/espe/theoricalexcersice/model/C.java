package ec.edu.espe.theoricalexcersice.model;

/**
 *
 * @author Kevin Chalan, OBJECT MASTER, OOP
 */
public class C extends B{
    private int c;
    private int d;
    
     public C(int c, int d, int a, int b) {
        super(a, b);
        this.c = c;
        this.d = d;
    }
    
    public void m(int p, int q){
        System.out.println("this method ---p "+p+"q"+q);
    }

    
    @Override
    public void m(int a) {
        System.out.println("method m of C that implemets of A");
        
    }

    @Override
    public String toString() {
        return "C{" + "c=" + c + ", d=" + d + super.toString() +'}';
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
