package ec.edu.espe.q47_60.Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steven Loza
 */
public abstract class A {
    private List<A> children = new ArrayList<>();

    public void addChild(A a) {
        children.add(a);
    }

    public abstract void describe();
}
