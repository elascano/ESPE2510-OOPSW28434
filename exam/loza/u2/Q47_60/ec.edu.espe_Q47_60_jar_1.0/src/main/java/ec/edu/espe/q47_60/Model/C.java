package ec.edu.espe.q47_60.Model;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steven Loza
 */
public class C extends A {
    private List<E> elements = new ArrayList<>();

    public void addE(E e) {
        elements.add(e);
    }

    @Override
    public void describe() {
        System.out.println("I am C with " + elements.size() + " E objects");
    }
}