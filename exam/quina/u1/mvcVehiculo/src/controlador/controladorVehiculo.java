package controlador;
import vista.vistaVehiculo;

import modelo.Vehiculo;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class controladorVehiculo {
     public static void main(String[] args) {
        // TODO code application logic here
        
        vistaVehiculo vista = new vistaVehiculo();
        String nombre = vista.ingresarNombre();
        String apellido = vista.ingresarApellido();
        String cedula = vista.ingresarCedula();
        String  direccion = vista.ingresarDireccion();
           String telefono = vista.ingresarTelefono();
              String placa = vista.ingresarPlaca();
                 String modelo = vista.ingresarModelo();
                    String color = vista.ingresarColor();
                       String tipo  = vista.ingresarTipo();
        
        
        Vehiculo aut = new Vehiculo(nombre, apellido, cedula, direccion, telefono,placa, modelo, color, tipo);
        
        vista.mostrarResultado(aut.mostrarInformacion());
  }
    
}
