
package ec.edu.espe.singleton.model;


import java.io.Serializable;
/**
 *
 * @author Thais Santorum
 */


public class Appointment implements Serializable {
    private String appointmentId;
    private String patientId;
    private String patientEmail;
    private String date;
    private String time;

    public Appointment(String appointmentId, String patientId, String patientEmail, String date, String time) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.patientEmail = patientEmail;
        this.date = date;
        this.time = time;
    }

    public String toFileString() {
        return getAppointmentId() + "," + getPatientId() + "," + getPatientEmail() + "," + getDate() + "," + getTime();
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    /**
     * @return the appointmentId
     */
    public String getAppointmentId() {
        return appointmentId;
    }

    /**
     * @param appointmentId the appointmentId to set
     */
    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * @return the patientId
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * @param patientEmail the patientEmail to set
     */
    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }
    
    
    
    
}


