/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package agendabancaria;
import java.util.Scanner;

/**
 *
 * @author aless
 */
public class AgendaBancaria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("---REGISTRO DE DATOS---");
        
        System.out.println("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        
        System.out.println("Ingrese su apellido: ");
        String apellido = sc.nextLine();        
        
        System.out.println("Ingrese el saldo inicial: ");
        double saldo = sc.nextDouble();        
        
        Persona p1 = new Persona(nombre, apellido);
        
        Cajero c1 = new Cajero(saldo);
        
        Bancario b1 = new Bancario();
        
        System.out.println("Datos obtenidos: ");
        
        p1.mostrarInfo();
        c1.mostrarSaldo();
        
        System.out.println("Ingrese el saldo a modificar (+ -): ");
        double cantidad = sc.nextDouble();
        
        b1.actualizarSaldo(c1, cantidad);
        
        System.out.println("Actualizacion: ");
        p1.mostrarInfo();
        c1.mostrarSaldo();
        

        


    }
    
}
