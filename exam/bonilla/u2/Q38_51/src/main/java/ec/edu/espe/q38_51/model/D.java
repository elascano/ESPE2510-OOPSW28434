package ec.edu.espe.q38_51.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */

public class D extends A {

    private List<E> elements = new ArrayList<>();
    private F component;

    public D(String name) {
        super(name);
        this.component = new F("Default F");
    }

    public void addElement(E e) {
        elements.add(e);
    }

    @Override
    public void display() {
        System.out.println("Class D: " + name);
        component.display();
        elements.forEach(E::display);
    }
}
