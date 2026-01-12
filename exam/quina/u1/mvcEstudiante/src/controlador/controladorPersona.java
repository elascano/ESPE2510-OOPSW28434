package controlador;

import vista.vistaPersona;

import modelo.; //por todos los atributos

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class controladorPersona {
    
    //aqui se instancia la vista
    
  public static void main(String[] args) {
        // TODO code application logic here
        
        vistaPersona vista = new vistaPersona();
        String nombre = vista.ingresarNombre();
        String apellido = vista.ingresarApellido();
        String cedula = vista.ingresarCedula();
        String direccion = vista.ingresarDireccion();
        String  = vista.ingresar();
       
        
        
        Estudiante estudiante = new Estudiante(nombre,apellido,cedula,carrera);
        
        vista.mostrarResultado(estudiante.mostrarInformacion());
  }
}
