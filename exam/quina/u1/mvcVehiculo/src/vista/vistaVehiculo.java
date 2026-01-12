package vista;
import java.util.Scanner;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */
public class vistaVehiculo {
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
         public String ingresarDireccion(){
        System.out.print("Ingresar direccion : ");
        return sc.nextLine();
    }
            public String ingresarTelefono(){
        System.out.print("Ingresar telefono: ");
        return sc.nextLine();
    }
               public String ingresarPlaca(){
        System.out.print("Ingresar placa: ");
        return sc.nextLine();
    }
                  public String ingresarModelo(){
        System.out.print("Ingresar modelo : ");
        return sc.nextLine();
    }
                     public String ingresarColor(){
        System.out.print("Ingresar color: ");
        return sc.nextLine();
    }
                        public String ingresarTipo(){
        System.out.print("Ingresar tipo: ");
        return sc.nextLine();
    }
         
         
         public void mostrarResultado(String informacion){
              System.out.print("---------LISTA DE DATOS-----------");
               System.out.print(informacion);
             
         }
    
    
}
