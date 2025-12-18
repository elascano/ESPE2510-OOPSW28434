package ec.edu.espe.q47_60.Model;

/**
 *
 * @author Steven Loza
 */
public class B extends A {
    private H handler;

    public B(H handler) {
        this.handler = handler;
    }

    @Override
    public void describe() {
        System.out.println("I am B");
        handler.execute();
    }
}


