package ec.espe.edu.chickenfarmsimulator.model;

public class Egg {
    private char size;

    // Constructor vacío
    public Egg() {
    }

    // Constructor con parámetro
    public Egg(char size) {
        this.size = size;
    }

    // Getter
    public char getSize() {
        return size;
    }

    // Setter
    public void setSize(char size) {
        this.size = size;
    }

    // toString
    @Override
    public String toString() {
        return "Egg{" + "size=" + size + '}';
    }
}

