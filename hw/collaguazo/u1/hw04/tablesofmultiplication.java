import java.util.Scanner;

public class TablasMultiplicar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String repetir;

        do {
            System.out.print("\n¿De qué número quieres ver la tabla de multiplicar? ");
            int n = sc.nextInt();

            System.out.println("\nTabla del " + n);
            System.out.println("----------------");

            for (int i = 1; i <= 10; i++) {
                System.out.println(n + " x " + i + " = " + (n * i));
            }

            System.out.print("\n¿Quieres ver otra tabla? (si/no): ");
            repetir = sc.next().toLowerCase();

        } while (repetir.equals("si"));

        System.out.println("\n¡Gracias por usar el programa! ");
        sc.close();
    }
}
