/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendabancaria;

/**
 *
 * @author aless
 */
public class Bancario {
    public void mostrarDatos(Persona p){
    p.mostrarInfo();
    
    }
    public void actualizarSaldo(Cajero c, double cantidad){
        c.modificarSaldo(cantidad);
    }
}
