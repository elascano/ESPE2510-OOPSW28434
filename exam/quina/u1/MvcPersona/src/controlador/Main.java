package controlador;
import vista.vistaAlumno;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class Main {
    
    public static void main (String[ ]args){
        vistaAlumno vista = new vistaAlumno();
        new controladorPersona(vista);
        vista.setVisible(true);
    }
    
}
