package controlador;
import modelo.Estudiante;
import vista.vistaAlumno;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class controladorPersona {
    
    private final vistaAlumno vista;
    
    public controladorPersona(vistaAlumno vista){
        
        this.vista=vista;
        this.vista.getbtnRegistar().addActionListener(e-> registrarEstudiante());
        
    
    }
    
    private void registrarEstudiante(){
        
       



double promedio = estudiante.calcularPromedio();
        
        Estudiante estudiante = new Estudiante(
 
 vista.getNombre(),
vista.getApellido(),
 vista.getCedula(),
    
  vista.getCarrera(),
                vista.getModalidad(),
                vista.getJornada(),
                vista.getSemestre(),
                vista.getPeriodo(),
                vista.getFacultad(),
                vista.getNota1(),
                  vista.getNota2(),
                  vista.getNota3()
                
        
                
                
        );
    
    String resultado = "<html>"
            
              +"NOMBRE: "+ vista.getNombre()+"<br>"
              +"APELLIDO: "+ vista.getApellido()+ "<br>"
            +"CEDULA: "+ vista.getCedula()+ "<br>"
              +"CARRERA: "+ vista.getCarrera()+ "<br>"
              +"MODALIDAD: "+ vista.getModalidad()+ "<br>"
             +"Jornada: "+ vista.getJornada()+ "<br>"
              +"SEMESTRE: "+ vista.getSemestre()+ "<br>"
              +"PERIODO: "+ vista.getPeriodo()+ "<br>"
              +"FACULTAD: "+ vista.getFacultad()+ "<br>"
                +"PROMEDIO: "+ promedio + "<br>"
            
         +"</html>";
    
    
    vista.mostrarResultado(resultado);
    
  
    }
}
//modalidad
//jornada
//semestre
//periodo
//facultad