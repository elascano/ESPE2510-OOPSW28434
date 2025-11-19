package herencia;
public class Empleado {
    
    private String nombre;
    private String cedula;
    private double salarioBase;
    
    //crear los constructores

    public Empleado(String nombre,String cedula,double salarioBase) {
                
        setNombre(nombre);
        setCedula(cedula);
        setSalarioBase(salarioBase);
    }
    
    //Setters
    
    public void setNombre(String nombre){
        if(nombre == null || nombre.trim().isEmpty()){
            System.out.println("Nombre invalido");
        }else {
            this.nombre = nombre;
        }
    }

    
    public void setCedula(String cedula){
        if(cedula != null && cedula.length() == 10){
            this.cedula = cedula;
        }else {
             System.out.println("Cedula invalida.");
        }
    }

    public void setSalarioBase(double salarioBase){
        this.salarioBase = salarioBase;
    }
        

//Getters
    public double getSalarioBase(){
        return salarioBase;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getCedula(){
        return cedula;
    }
    
 //Metodo general   
    public void mostrarInfo(){
        System.out.println("Nombre: " + nombre);
        System.out.println("Cedula: " + cedula);
        System.out.println("Salario Base: " + salarioBase);

    
    }
    public void validarSalario(double salariobase) throws SalarioException {
        if (salariobase < 0 || salariobase > 2000){
            throw  new SalarioException("Error: El salario: " + salariobase + " no cumple con los parametros hasta 2000 o es negativo");
        }
    }
    
}



