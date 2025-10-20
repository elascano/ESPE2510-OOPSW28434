public class TablasMultiplicar {
    public static void main(String[] args) {
        // Mostrar las tablas de multiplicar del 1 al 12
        for (int i = 1; i <= 12; i++) {
            System.out.println("Tabla del " + i + ":");
            for (int j = 1; j <= 12; j++) {
                System.out.println(i + " x " + j + " = " + (i * j));
            }
            System.out.println("--------------------");
        }
    }
}
