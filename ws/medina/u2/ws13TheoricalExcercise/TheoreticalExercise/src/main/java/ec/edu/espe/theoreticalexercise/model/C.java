package ec.edu.espe.theoreticalexercise.model;

/**
 *
 * @author Joseph B. Medina
 */
public class C extends B{
    private int c;
    private int d;

    public C(int c, int d, int a, int b) {
        super(a, b);
        this.c = c;
        this.d = d;
    }   

    @Override
    public String toString() {
        return "C{"+ "..." + super.toString() + "c=" + c + ", d=" + d + '}';
    }
    
    
    
    public void m(int p, int q){
        System.out.println("This is the method m with prameters p and q");
}

    @Override
    public void m(int a) {
        System.out.println("method m of c");
        
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }
    
    
    
}
