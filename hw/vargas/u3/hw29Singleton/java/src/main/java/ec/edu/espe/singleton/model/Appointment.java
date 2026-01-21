package ec.edu.espe.singleton.model;
/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */

public class Appointment {

    private int appointmentId;
    private Date date; 
    private String status;
    private int patientId;
    private int doctorId;

    public Appointment() {
    }

    public Appointment(int appointmentId, Date date, int patientId, int doctorId) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.status = "Agendado"; 
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "-------------------------"+
                "\nCITA" + "(" + appointmentId +")"+ 
                "\nFecha: " + date + 
                "\nEstado: " + status + 
                "\nID del paciente: " + patientId + 
                "\nID del Doctor: " + doctorId + 
                "\n-------------------------";  
    }
    
}