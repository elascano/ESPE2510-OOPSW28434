package ec.edu.espe.theoricalexercise.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class C extends B{
    private int c;
    private int d;
    
    @Override
    public void m(int a) {
        System.out.println("Method m of C implements of A");
    }

    @Override
    public String toString() {
        return "C{" + super.toString() + " c = " + c + ", d = " + d + '}';
    }
    
    public C(int c, int d, int a, int b) {
        super(a, b);
        this.c = c;
        this.d = d;
    }
    
    public void m(int q, int p){
        System.out.println("This is method m() with parameters -- p " + p + "and -- q "+ q);
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
