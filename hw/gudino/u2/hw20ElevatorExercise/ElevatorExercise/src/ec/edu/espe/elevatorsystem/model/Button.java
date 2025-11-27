package ec.edu.espe.elevatorsystem.model;

public abstract class Button {
    protected boolean isPressed;
    protected int id;

    public Button(int id) {
        this.id = id;
        this.isPressed = false;
    }

    public abstract void press();

    public boolean isPressed() { return isPressed; }
    public int getId() { return id; }
}
