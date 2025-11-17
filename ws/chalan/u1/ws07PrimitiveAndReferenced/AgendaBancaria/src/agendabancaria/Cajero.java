/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendabancaria;

/**
 *
 * @author aless
 */
public class Cajero {
     String nombreBanco;
     private double saldoInicial;
    
    public Cajero (double SaldoInicial){
        saldoInicial = SaldoInicial;
    
    }
    
    public void modificarSaldo(double cantidad){
        saldoInicial += cantidad;
    }
    
    public void mostrarSaldo(){
        System.out.println("Su saldo es: " + saldoInicial);
    }
    
}
