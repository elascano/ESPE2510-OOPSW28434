package ec.edu.espe.q47_60.Model;

/**
 *
 * @author Steven Loza
 */
import java.util.ArrayList;
import java.util.List;

public class D extends A {
    private List<E> elements = new ArrayList<>();
    private F f; // composition

    public D() {
        this.f = new F();
    }

    public void addE(E e) {
        elements.add(e);
    }

    @Override
    public void describe() {
        System.out.println("I am D with " + elements.size() + " E objects and one F");
        f.describe();
    }
}