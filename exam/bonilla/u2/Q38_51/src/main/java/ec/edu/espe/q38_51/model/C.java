package ec.edu.espe.q38_51.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */

public class C extends A {

    private List<E> elements = new ArrayList<>();

    public C(String name) {
        super(name);
    }

    public void addElement(E e) {
        elements.add(e);
    }

    @Override
    public void display() {
        System.out.println("Class C: " + name);
        elements.forEach(E::display);
    }
}
