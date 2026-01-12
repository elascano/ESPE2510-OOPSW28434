package vista;


import java.util.Scanner;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class vistaPersona {
    
    private Scanner sc = new Scanner(System.in);
    
    //metodo que retorne string para q solo ingrese cedula
    
    
    public String ingresarNombre(){
        System.out.print("Ingresar nombre: ");
        return sc.nextLine();
    }
    public String ingresarApellido(){
        System.out.print("Ingresar apellido: ");
        return sc.nextLine();
    }
        public String ingresarCedula(){
        System.out.print("Ingresar cedula: ");
        return sc.nextLine();
    }
         public String ingresarCarrera(){
        System.out.print("Ingresar carrera: ");
        return sc.nextLine();
    }
         
         
         public void mostrarResultado(String informacion){
              System.out.print("---------LISTA DE DATOS-----------");
               System.out.print(informacion);
             
         }
    
}
