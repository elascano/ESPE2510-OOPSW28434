package ec.edu.espe.q38_51.model;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class B extends A {

    private H service;

    public B(String name, H service) {
        super(name);
        this.service = service;
    }

    @Override
    public void display() {
        System.out.println("Class B: " + name);
        service.execute();
    }
}

