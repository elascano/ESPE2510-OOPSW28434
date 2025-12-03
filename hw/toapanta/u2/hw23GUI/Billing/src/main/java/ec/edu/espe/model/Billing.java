/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espe.model;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class Billing {
    
    // 1. Atributos (campos o propiedades)
    private String patientId;        // Corresponde a la Cédula
    private String patientName;      // Corresponde al Nombre
    private String patientAddress;   // Corresponde a la Dirección de Casa
    private float cost;              // Corresponde al Costo a Cobrar

    // 2. Constructor
    
    /**
     * Constructor por defecto (necesario para algunas operaciones de Java).
     */
    public Billing() {
    }

    /**
     * Constructor para inicializar todos los atributos de la factura.
     * * @param patientId La cédula o ID del paciente.
     * @param patientName El nombre completo del paciente.
     * @param patientAddress La dirección de casa del paciente.
     * @param cost El costo total a cobrar (en flotante).
     */
    public Billing(String patientId, String patientName, String patientAddress, float cost) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientAddress = patientAddress;
        this.cost = cost;
    }

    // 3. Getters y Setters (Métodos de Acceso y Modificación)

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
    


    @Override
    public String toString() {
        return "Billing{" + 
                "patientId='" + patientId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientAddress='" + patientAddress + '\'' +
                ", cost=" + String.format("%.2f", cost) + 
                '}';
    }
}