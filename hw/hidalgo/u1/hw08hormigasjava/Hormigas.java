
class Hormiga {
    private String nombre;
    private int edad;
    private String color;
    private boolean viva;

    public Hormiga(String nombre, int edad, String color) {
        this.nombre = nombre;
        this.edad = edad;
        this.color = color;
        this.viva = true;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad + " años");
        System.out.println("Color: " + color);
        System.out.println("Estado: " + (viva ? "Viva" : "Muerta"));
    }

    public void caminar() {
        System.out.println(nombre + (viva ? " está caminando." : " no puede caminar, está muerta."));
    }

    public void comer() {
        System.out.println(nombre + (viva ? " está comiendo." : " no puede comer, está muerta."));
    }

    public void dormir() {
        System.out.println(nombre + (viva ? " está durmiendo." : " no puede dormir, está muerta."));
    }

    public void morir() {
        viva = false;
        System.out.println(nombre + " ha muerto.");
    }
}


public class HormigaMain {
    public static void main(String[] args) {
        Hormiga h1 = new Hormiga("Ana", 1, "Roja");
        Hormiga h2 = new Hormiga("Luis", 2, "Negra");

        h1.mostrarInformacion();
        h2.mostrarInformacion();

        h1.caminar();
        h2.comer();
        h1.dormir();
        h2.morir();
        h2.caminar();
    }
}
