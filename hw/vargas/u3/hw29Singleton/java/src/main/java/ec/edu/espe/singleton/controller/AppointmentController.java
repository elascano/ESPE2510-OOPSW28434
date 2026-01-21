
package ec.edu.espe.singleton.controller;

import ec.edu.espe.singleton.model.Appointment;
import java.util.Date;



/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
public class AppointmentController {



    public void cancel(Appointment appointment) {
        appointment.setStatus("Cancelada");
        System.out.println("Cita " + appointment.getAppointmentId() + " ha sido cancelada.");
    }


    public void markCompleted(Appointment appointment) {
        appointment.setStatus("Completada");
        System.out.println("Cita " + appointment.getAppointmentId() + " marcada como completada.");
    }
}
