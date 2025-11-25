package ec.edu.espe.theoreticalexercise.model;

/**
 *
 * @author Arelys Otavalo
 */
//es una clase como cualquier otra entonces puedo poner setters y getters
public abstract class B implements A{
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

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    
}

